package com.pandahis.dashboard.modules.system.controller.user;

import com.pandahis.dashboard.common.pojo.CommonResult;
import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.excel.core.util.ExcelUtils;
import com.pandahis.dashboard.framework.logger.operatelog.core.annotations.OperateLog;
import com.pandahis.dashboard.framework.logger.operatelog.core.enums.OperateTypeEnum;
import com.pandahis.dashboard.modules.system.controller.user.vo.user.*;
import com.pandahis.dashboard.modules.system.convert.user.SysUserConvert;
import com.pandahis.dashboard.modules.system.dal.dataobject.dept.SysDeptDO;
import com.pandahis.dashboard.modules.system.dal.dataobject.user.SysUserDO;
import com.pandahis.dashboard.modules.system.service.dept.SysDeptService;
import com.pandahis.dashboard.modules.system.service.user.SysUserService;
import com.pandahis.dashboard.util.collection.CollectionUtils;
import com.pandahis.dashboard.util.collection.MapUtils;
import cn.hutool.core.collection.CollUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Api(tags = "用户")
@RestController
@RequestMapping("/system/user")
@Validated
public class SysUserController {

    @Resource
    private SysUserService userService;
    @Resource
    private SysDeptService deptService;

    @PostMapping("/create")
    @ApiOperation("新增用户")
    public CommonResult<Long> createUser(@Valid @RequestBody SysUserCreateReqVO reqVO) {
        Long id = userService.createUser(reqVO);
        return CommonResult.success(id);
    }

    @PostMapping("/register")
    @ApiOperation("注册用户")
    public CommonResult<Long> registerUser( @RequestBody SysUserCreateReqVO reqVO) {
        Long id = userService.registerUser(reqVO);
        return CommonResult.success(id);
    }



    @PutMapping("update")
    @ApiOperation("修改用户")
    @PreAuthorize("@ss.hasPermission('system:user:update')")
    public CommonResult<Boolean> updateUser(@Valid @RequestBody SysUserUpdateReqVO reqVO) {
        userService.updateUser(reqVO);
        return CommonResult.success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除用户")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('system:user:delete')")
    public CommonResult<Boolean> deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return CommonResult.success(true);
    }

    @PutMapping("/update-password")
    @ApiOperation("重置用户密码")
    @PreAuthorize("@ss.hasPermission('system:user:update-password')")
    public CommonResult<Boolean> updateUserPassword(@Valid @RequestBody SysUserUpdatePasswordReqVO reqVO) {
        userService.updateUserPassword(reqVO.getId(), reqVO.getPassword());
        return CommonResult.success(true);
    }

    @PutMapping("/update-status")
    @ApiOperation("修改用户状态")
    @PreAuthorize("@ss.hasPermission('system:user:update')")
    public CommonResult<Boolean> updateUserStatus(@Valid @RequestBody SysUserUpdateStatusReqVO reqVO) {
        userService.updateUserStatus(reqVO.getId(), reqVO.getStatus());
        return CommonResult.success(true);
    }

    @GetMapping("/page")
    @ApiOperation("获得用户分页列表")
    @PreAuthorize("@ss.hasPermission('system:user:list')")
    public CommonResult<PageResult<SysUserPageItemRespVO>> getUserPage(@Valid SysUserPageReqVO reqVO) {
        // 获得用户分页列表
        PageResult<SysUserDO> pageResult = userService.getUserPage(reqVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return CommonResult.success(new PageResult<>(pageResult.getTotal())); // 返回空
        }

        // 获得拼接需要的数据
        Collection<Long> deptIds = CollectionUtils.convertList(pageResult.getList(), SysUserDO::getDeptId);
        Map<Long, SysDeptDO> deptMap = deptService.getDeptMap(deptIds);
        // 拼接结果返回
        List<SysUserPageItemRespVO> userList = new ArrayList<>(pageResult.getList().size());
        pageResult.getList().forEach(user -> {
            SysUserPageItemRespVO respVO = SysUserConvert.INSTANCE.convert(user);
            respVO.setDept(SysUserConvert.INSTANCE.convert(deptMap.get(user.getDeptId())));
            userList.add(respVO);
        });
        return CommonResult.success(new PageResult<>(userList, pageResult.getTotal()));
    }

    @GetMapping("/get")
    @ApiOperation("获得用户详情")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('system:user:query')")
    public CommonResult<SysUserRespVO> getInfo(@RequestParam("id") Long id) {
        return CommonResult.success(SysUserConvert.INSTANCE.convert(userService.getUser(id)));
    }

    @GetMapping("/export")
    @ApiOperation("导出用户")
    @PreAuthorize("@ss.hasPermission('system:user:export')")
    @OperateLog(type = OperateTypeEnum.EXPORT)
    public void exportUsers(@Validated SysUserExportReqVO reqVO,
                            HttpServletResponse response) throws IOException {
        // 获得用户列表
        List<SysUserDO> users = userService.getUsers(reqVO);

        // 获得拼接需要的数据
        Collection<Long> deptIds = CollectionUtils.convertList(users, SysUserDO::getDeptId);
        Map<Long, SysDeptDO> deptMap = deptService.getDeptMap(deptIds);
        // 拼接数据
        List<SysUserExcelVO> excelUsers = new ArrayList<>(users.size());
        users.forEach(user -> {
            SysUserExcelVO excelVO = SysUserConvert.INSTANCE.convert02(user);
            MapUtils.findAndThen(deptMap, user.getDeptId(), dept -> {
                excelVO.setDeptName(dept.getName());
                excelVO.setDeptLeader(dept.getLeader());
            });
            excelUsers.add(excelVO);
        });

        // 输出
        ExcelUtils.write(response, "用户数据.xls", "用户列表", SysUserExcelVO.class, excelUsers);
    }

    @GetMapping("/get-import-template")
    @ApiOperation("获得导入用户模板")
    public void importTemplate(HttpServletResponse response) throws IOException {


        // 输出
        ExcelUtils.write(response, "用户导入模板.xls", "用户列表", SysUserImportExcelVO.class, null);
    }

    @PostMapping("/import")
    @ApiOperation("导入用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "Excel 文件", required = true, dataTypeClass = MultipartFile.class),
            @ApiImplicitParam(name = "updateSupport", value = "是否支持更新，默认为 false", example = "true", dataTypeClass = Boolean.class)
    })
    @PreAuthorize("@ss.hasPermission('system:user:import')")
    public CommonResult<SysUserImportRespVO> importExcel(@RequestParam("file") MultipartFile file,
                                                         @RequestParam(value = "updateSupport", required = false, defaultValue = "false") Boolean updateSupport) throws Exception {
        List<SysUserImportExcelVO> list = ExcelUtils.raed(file, SysUserImportExcelVO.class);
        return CommonResult.success(userService.importUsers(list, updateSupport));
    }


    @RequestMapping("/getUserByUsername")
    public CommonResult getUserByUserName(@Param("username")String username){
        return  CommonResult.success(userService.getUserByUsername(username));
    }



    @RequestMapping("/getUserPagesByUserName")
    public CommonResult getUserPagesByUserName(@RequestBody SysUserPageReqVO vo){
        return  CommonResult.success(userService.getUserPagesByUserName(vo));
    }


    @RequestMapping("/initAllUser")
    public CommonResult initAllUser(@RequestBody SysUserRespVO vo){
        return  CommonResult.success(userService.initAllUser(vo));
    }


    @RequestMapping("/queryPetUser")
    public CommonResult queryPetUser(@RequestBody SysUserPageReqVO vo){
        return  CommonResult.success(userService.queryPetUser(vo));
    }



    @RequestMapping("/getUsersByDeptName")
    public CommonResult getUsersByDeptName(@RequestBody SysUserRespVO vo){
        return  CommonResult.success(userService.getUsersByDeptName(vo));
    }


    @RequestMapping("/getUserByIdCard")
    public CommonResult getUserByUserName(@RequestBody SysUserRespVO sysUserRespVO){
        return  CommonResult.success(userService.getUserByIdCard(sysUserRespVO));
    }

}

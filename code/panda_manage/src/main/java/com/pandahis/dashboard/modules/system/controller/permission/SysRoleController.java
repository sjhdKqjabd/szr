package com.pandahis.dashboard.modules.system.controller.permission;

import com.pandahis.dashboard.common.enums.CommonStatusEnum;
import com.pandahis.dashboard.common.pojo.CommonResult;
import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.excel.core.util.ExcelUtils;
import com.pandahis.dashboard.framework.logger.operatelog.core.annotations.OperateLog;
import com.pandahis.dashboard.modules.system.controller.permission.vo.role.*;
import com.pandahis.dashboard.modules.system.convert.permission.SysRoleConvert;
import com.pandahis.dashboard.modules.system.dal.dataobject.permission.SysRoleDO;
import com.pandahis.dashboard.modules.system.service.permission.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.pandahis.dashboard.framework.logger.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Api(tags = "角色")
@RestController
@RequestMapping("/system/role")
@Validated
public class SysRoleController {

    @Resource
    private SysRoleService roleService;

    @PostMapping("/create")
    @ApiOperation("创建角色")
    @PreAuthorize("@ss.hasPermission('system:role:create')")
    public CommonResult<Long> createRole(@Valid @RequestBody SysRoleCreateReqVO reqVO) {
        return CommonResult.success(roleService.createRole(reqVO));
    }

    @PutMapping("/update")
    @ApiOperation("修改角色")
    @PreAuthorize("@ss.hasPermission('system:role:update')")
    public CommonResult<Boolean> updateRole(@Valid @RequestBody SysRoleUpdateReqVO reqVO) {
        roleService.updateRole(reqVO);
        return CommonResult.success(true);
    }

    @PutMapping("/update-status")
    @ApiOperation("修改角色状态")
    @PreAuthorize("@ss.hasPermission('system:role:update')")
    public CommonResult<Boolean> updateRoleStatus(@Valid @RequestBody SysRoleUpdateStatusReqVO reqVO) {
        roleService.updateRoleStatus(reqVO.getId(), reqVO.getStatus());
        return CommonResult.success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除角色")
    @ApiImplicitParam(name = "id", value = "角色编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('system:role:delete')")
    public CommonResult<Boolean> deleteRole(@RequestParam("id") Long id) {
        roleService.deleteRole(id);
        return CommonResult.success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得角色信息")
    @PreAuthorize("@ss.hasPermission('system:role:query')")
    public CommonResult<SysRoleRespVO> getRole(@RequestParam("id") Long id) {
        SysRoleDO role = roleService.getRole(id);
        return CommonResult.success(SysRoleConvert.INSTANCE.convert(role));
    }

    @GetMapping("/page")
    @ApiOperation("获得角色分页")
    public CommonResult<PageResult<SysRoleDO>> getRolePage(SysRolePageReqVO reqVO) {
        return CommonResult.success(roleService.getRolePage(reqVO));
    }

    @GetMapping("/list-all-simple")
    @ApiOperation(value = "获取角色精简信息列表", notes = "只包含被开启的角色，主要用于前端的下拉选项")
    public CommonResult<List<SysRoleSimpleRespVO>> getSimpleRoles() {
        // 获得角色列表，只要开启状态的
        List<SysRoleDO> list = roleService.getRoles(Collections.singleton(CommonStatusEnum.ENABLE.getStatus()));
        // 排序后，返回个诶前端
        list.sort(Comparator.comparing(SysRoleDO::getSort));
        return CommonResult.success(SysRoleConvert.INSTANCE.convertList02(list));
    }

    @GetMapping("/export")
    @OperateLog(type = EXPORT)
    @PreAuthorize("@ss.hasPermission('system:role:export')")
    public void export(HttpServletResponse response, @Validated SysRoleExportReqVO reqVO) throws IOException {
        List<SysRoleDO> list = roleService.getRoles(reqVO);
        List<SysRoleExcelVO> data = SysRoleConvert.INSTANCE.convertList03(list);
        // 输出
        ExcelUtils.write(response, "角色数据.xls", "角色列表", SysRoleExcelVO.class, data);
    }

}

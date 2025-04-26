package com.pandahis.dashboard.modules.system.controller.auth;

import com.pandahis.dashboard.common.pojo.CommonResult;
import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.system.convert.auth.SysUserSessionConvert;
import com.pandahis.dashboard.modules.system.dal.dataobject.auth.SysUserSessionDO;
import com.pandahis.dashboard.modules.system.dal.dataobject.dept.SysDeptDO;
import com.pandahis.dashboard.modules.system.dal.dataobject.user.SysUserDO;
import com.pandahis.dashboard.modules.system.service.auth.SysUserSessionService;
import com.pandahis.dashboard.modules.system.service.dept.SysDeptService;
import com.pandahis.dashboard.modules.system.service.user.SysUserService;
import com.pandahis.dashboard.util.collection.CollectionUtils;
import com.pandahis.dashboard.util.collection.MapUtils;
import com.pandahis.dashboard.modules.system.controller.auth.vo.session.SysUserSessionPageItemRespVO;
import com.pandahis.dashboard.modules.system.controller.auth.vo.session.SysUserSessionPageReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api(tags = "用户 Session")
@RestController
@RequestMapping("/system/user-session")
public class SysUserSessionController {

    @Resource
    private SysUserSessionService userSessionService;
    @Resource
    private SysUserService userService;
    @Resource
    private SysDeptService deptService;

    @GetMapping("/page")
    @ApiOperation("获得 Session 分页列表")
    @PreAuthorize("@ss.hasPermission('system:user-session:page')")
    public CommonResult<PageResult<SysUserSessionPageItemRespVO>> getUserSessionPage(@Validated SysUserSessionPageReqVO reqVO) {
        // 获得 Session 分页
        PageResult<SysUserSessionDO> pageResult = userSessionService.getUserSessionPage(reqVO);

        // 获得拼接需要的数据
        Map<Long, SysUserDO> userMap = userService.getUserMap(
                CollectionUtils.convertList(pageResult.getList(), SysUserSessionDO::getUserId));
        Map<Long, SysDeptDO> deptMap = deptService.getDeptMap(
                CollectionUtils.convertList(userMap.values(), SysUserDO::getDeptId));
        // 拼接结果返回
        List<SysUserSessionPageItemRespVO> sessionList = new ArrayList<>(pageResult.getList().size());
        pageResult.getList().forEach(session -> {
            SysUserSessionPageItemRespVO respVO = SysUserSessionConvert.INSTANCE.convert(session);
            sessionList.add(respVO);
            // 设置用户账号
            MapUtils.findAndThen(userMap, session.getUserId(), user -> {
                respVO.setUsername(user.getUsername());
                // 设置用户科室
                MapUtils.findAndThen(deptMap, user.getDeptId(), dept -> respVO.setDeptName(dept.getName()));
            });
        });
        return CommonResult.success(new PageResult<>(sessionList, pageResult.getTotal()));
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除 Session")
    @ApiImplicitParam(name = "id", value = "Session 编号", required = true, dataTypeClass = String.class,
            example = "fe50b9f6-d177-44b1-8da9-72ea34f63db7")
    @PreAuthorize("@ss.hasPermission('system:user-session:delete')")
    public CommonResult<Boolean> deleteUserSession(@RequestParam("id") String id) {
        userSessionService.deleteUserSession(id);
        return CommonResult.success(true);
    }

}

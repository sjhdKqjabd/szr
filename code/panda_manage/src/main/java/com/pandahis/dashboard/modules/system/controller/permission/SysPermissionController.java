package com.pandahis.dashboard.modules.system.controller.permission;

import com.pandahis.dashboard.modules.system.controller.permission.vo.permission.SysPermissionAssignRoleDataScopeReqVO;
import com.pandahis.dashboard.modules.system.controller.permission.vo.permission.SysPermissionAssignRoleMenuReqVO;
import com.pandahis.dashboard.modules.system.controller.permission.vo.permission.SysPermissionAssignUserRoleReqVO;
import com.pandahis.dashboard.common.pojo.CommonResult;
import com.pandahis.dashboard.modules.system.service.permission.SysPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Set;

/**
 * 权限 Controller，提供赋予用户、角色的权限的 API 接口
 *
 * @author 源码乐园
 */
@Api(tags = "权限")
@RestController
@RequestMapping("/system/permission")
public class SysPermissionController {

    @Resource
    private SysPermissionService permissionService;

    @ApiOperation("获得角色拥有的菜单编号")
    @ApiImplicitParam(name = "roleId", value = "角色编号", required = true, dataTypeClass = Long.class)
    @GetMapping("/list-role-resources")
//    @RequiresPermissions("system:permission:assign-role-menu")
    public CommonResult<Set<Long>> listRoleMenus(Long roleId) {
        return CommonResult.success(permissionService.listRoleMenuIds(roleId));
    }

    @PostMapping("/assign-role-menu")
    @ApiOperation("赋予角色菜单")
//    @RequiresPermissions("system:permission:assign-role-resource")
    public CommonResult<Boolean> assignRoleMenu(@Validated @RequestBody SysPermissionAssignRoleMenuReqVO reqVO) {
        permissionService.assignRoleMenu(reqVO.getRoleId(), reqVO.getMenuIds());
        return CommonResult.success(true);
    }

    @PostMapping("/assign-role-data-scope")
    @ApiOperation("赋予角色数据权限")
//    @RequiresPermissions("system:permission:assign-role-data-scope")
    public CommonResult<Boolean> assignRoleDataScope(
            @Validated @RequestBody SysPermissionAssignRoleDataScopeReqVO reqVO) {
        permissionService.assignRoleDataScope(reqVO.getRoleId(), reqVO.getDataScope(), reqVO.getDataScopeDeptIds());
        return CommonResult.success(true);
    }

    @ApiOperation("获得管理员拥有的角色编号列表")
    @ApiImplicitParam(name = "userId", value = "用户编号", required = true, dataTypeClass = Long.class)
    @GetMapping("/list-user-roles")
//    @RequiresPermissions("system:permission:assign-user-role")
    public CommonResult<Set<Long>> listAdminRoles(@RequestParam("userId") Long userId) {
        return CommonResult.success(permissionService.listUserRoleIs(userId));
    }

    @ApiOperation("赋予用户角色")
    @PostMapping("/assign-user-role")
//    @RequiresPermissions("system:permission:assign-user-role")
    public CommonResult<Boolean> assignUserRole(@Validated @RequestBody SysPermissionAssignUserRoleReqVO reqVO) {
        permissionService.assignUserRole(reqVO.getUserId(), reqVO.getRoleIds());
        return CommonResult.success(true);
    }

}

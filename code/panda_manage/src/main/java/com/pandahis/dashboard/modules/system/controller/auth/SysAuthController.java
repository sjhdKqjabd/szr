package com.pandahis.dashboard.modules.system.controller.auth;

import com.pandahis.dashboard.common.enums.CommonStatusEnum;
import com.pandahis.dashboard.common.pojo.CommonResult;
import com.pandahis.dashboard.framework.logger.operatelog.core.annotations.OperateLog;
import com.pandahis.dashboard.framework.security.core.util.SecurityFrameworkUtils;
import com.pandahis.dashboard.modules.system.convert.auth.SysAuthConvert;
import com.pandahis.dashboard.modules.system.dal.dataobject.permission.SysMenuDO;
import com.pandahis.dashboard.modules.system.dal.dataobject.permission.SysRoleDO;
import com.pandahis.dashboard.modules.system.dal.dataobject.user.SysUserDO;
import com.pandahis.dashboard.modules.system.enums.permission.MenuTypeEnum;
import com.pandahis.dashboard.modules.system.service.auth.SysAuthService;
import com.pandahis.dashboard.modules.system.service.user.SysUserService;
import com.pandahis.dashboard.util.collection.SetUtils;
import com.pandahis.dashboard.util.servlet.ServletUtils;
import com.pandahis.dashboard.modules.system.controller.auth.vo.auth.SysAuthLoginReqVO;
import com.pandahis.dashboard.modules.system.controller.auth.vo.auth.SysAuthLoginRespVO;
import com.pandahis.dashboard.modules.system.controller.auth.vo.auth.SysAuthMenuRespVO;
import com.pandahis.dashboard.modules.system.controller.auth.vo.auth.SysAuthPermissionInfoRespVO;
import com.pandahis.dashboard.modules.system.service.permission.SysPermissionService;
import com.pandahis.dashboard.modules.system.service.permission.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static com.pandahis.dashboard.util.servlet.ServletUtils.getUserAgent;

@Api(tags = "认证")
@RestController
@RequestMapping("/")
@Validated
public class SysAuthController {

    @Resource
    private SysAuthService authService;
    @Resource
    private SysUserService userService;
    @Resource
    private SysRoleService roleService;
    @Resource
    private SysPermissionService permissionService;


    @Resource
    private PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    @ApiOperation("使用账号密码登录")
    @OperateLog(enable = false) // 避免 Post 请求被记录操作日志
    public CommonResult<SysAuthLoginRespVO> login(@RequestBody @Valid SysAuthLoginReqVO reqVO) {
        String token = authService.login(reqVO, ServletUtils.getClientIP(), ServletUtils.getUserAgent());

        return CommonResult.success(SysAuthLoginRespVO.builder().token(token).build());
    }

    @GetMapping("/get-permission-info")
    @ApiOperation("获取登陆用户的权限信息")
    public CommonResult<SysAuthPermissionInfoRespVO> getPermissionInfo() {
        // 获得用户信息
        SysUserDO user = userService.getUser(SecurityFrameworkUtils.getLoginUserId());
        if (user == null) {
            return null;
        }
        // 获得角色列表
        List<SysRoleDO> roleList = roleService.getRolesFromCache(SecurityFrameworkUtils.getLoginUserRoleIds());
        // 获得菜单列表
        List<SysMenuDO> menuList = permissionService.getRoleMenusFromCache(
                SecurityFrameworkUtils.getLoginUserRoleIds(), // 注意，基于登陆的角色，因为后续的权限判断也是基于它
                SetUtils.asSet(MenuTypeEnum.DIR.getType(), MenuTypeEnum.MENU.getType(), MenuTypeEnum.BUTTON.getType()),
                SetUtils.asSet(CommonStatusEnum.ENABLE.getStatus()));
        // 拼接结果返回
        return CommonResult.success(SysAuthConvert.INSTANCE.convert(user, roleList, menuList));
    }

    @GetMapping("list-menus")
    @ApiOperation("获得登陆用户的菜单列表")
    public CommonResult<List<SysAuthMenuRespVO>> getMenus() {
        // 获得用户拥有的菜单列表
        List<SysMenuDO> menuList = permissionService.getRoleMenusFromCache(
                SecurityFrameworkUtils.getLoginUserRoleIds(), // 注意，基于登陆的角色，因为后续的权限判断也是基于它
                SetUtils.asSet(MenuTypeEnum.DIR.getType(), MenuTypeEnum.MENU.getType()), // 只要目录和菜单类型
                SetUtils.asSet(CommonStatusEnum.ENABLE.getStatus())); // 只要开启的
        // 转换成 Tree 结构返回
        return CommonResult.success(SysAuthConvert.INSTANCE.buildMenuTree(menuList));
    }

}

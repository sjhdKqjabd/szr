package com.pandahis.dashboard.framework.security.core.service;

import com.pandahis.dashboard.modules.system.dal.dataobject.permission.SysRoleDO;

/**
 * Security 框架 Permission Service 接口，定义 security 组件需要的功能
 *
 * @author 源码乐园
 */
public interface SecurityPermissionFrameworkService {

    /**
     * 判断是否有权限
     *
     * @param permission 权限
     * @return 是否
     */
    boolean hasPermission(String permission);

    /**
     * 判断是否有权限，任一一个即可
     *
     * @param permissions 权限
     * @return 是否
     */
    boolean hasAnyPermissions(String... permissions);

    /**
     * 判断是否有角色
     *
     * 注意，角色使用的是 {@link SysRoleDO#getCode()} 标识
     *
     * @param role 角色
     * @return 是否
     */
    boolean hasRole(String role);

    /**
     * 判断是否有角色，任一一个即可
     *
     * @param roles 角色数组
     * @return 是否
     */
    boolean hasAnyRoles(String... roles);

}

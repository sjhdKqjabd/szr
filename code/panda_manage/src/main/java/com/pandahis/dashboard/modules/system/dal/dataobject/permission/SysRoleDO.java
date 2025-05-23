package com.pandahis.dashboard.modules.system.dal.dataobject.permission;

import com.pandahis.dashboard.common.enums.CommonStatusEnum;
import com.pandahis.dashboard.framework.mybatis.core.dataobject.BaseDO;
import com.pandahis.dashboard.framework.mybatis.core.type.JsonLongSetTypeHandler;
import com.pandahis.dashboard.framework.security.core.enums.DataScopeEnum;
import com.pandahis.dashboard.modules.system.enums.permission.RoleCodeEnum;
import com.pandahis.dashboard.modules.system.enums.permission.SysRoleTypeEnum;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * 角色 DO
 *
 *
 */
@TableName(value = "sys_role", autoResultMap = true)
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleDO extends BaseDO {

    /**
     * 角色ID
     */
    @TableId
    private Long id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色标识
     *
     * 枚举 {@link RoleCodeEnum}
     */
    private String code;
    /**
     * 角色排序
     */
    private Integer sort;
    /**
     * 角色状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 角色类型
     *
     * 枚举 {@link SysRoleTypeEnum}
     */
    private Integer type;
    /**
     * 备注
     */
    private String remark;

    /**
     * 数据范围
     *
     * 枚举 {@link DataScopeEnum}
     */
    private Integer dataScope;
    /**
     * 数据范围(指定科室数组)
     *
     * 适用于 {@link #dataScope} 的值为 {@link DataScopeEnum#DEPT_CUSTOM} 时
     */
    @TableField(typeHandler = JsonLongSetTypeHandler.class)
    private Set<Long> dataScopeDeptIds;

}

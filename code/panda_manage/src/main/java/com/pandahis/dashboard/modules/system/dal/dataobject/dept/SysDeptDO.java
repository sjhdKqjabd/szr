package com.pandahis.dashboard.modules.system.dal.dataobject.dept;

import com.pandahis.dashboard.common.enums.CommonStatusEnum;
import com.pandahis.dashboard.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 科室表
 *
 *
 */
@TableName("sys_dept")
@Data
@EqualsAndHashCode(callSuper = true)

public class SysDeptDO extends BaseDO {

    /**
     * 科室ID
     */
    @TableId
    private Long id;
    /**
     * 科室名称
     */
    private String name;
    /**
     * 父科室ID
     *
     * 关联 {@link #id}
     */
    private Long parentId;
    /**
     * 显示顺序
     */
    private Integer sort;
    /**
     * 负责人
     */
    private String leader;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 科室状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;

}

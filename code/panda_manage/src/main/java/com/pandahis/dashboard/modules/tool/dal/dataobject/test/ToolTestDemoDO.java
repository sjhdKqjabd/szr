package com.pandahis.dashboard.modules.tool.dal.dataobject.test;

import com.pandahis.dashboard.common.enums.CommonStatusEnum;
import com.pandahis.dashboard.framework.logger.operatelog.core.enums.OperateTypeEnum;
import com.pandahis.dashboard.framework.mybatis.core.dataobject.BaseDO;
import com.pandahis.dashboard.framework.redis.core.RedisKeyDefine;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
* 字典类型 DO
*
* @author 源码乐园
*/
@TableName("tool_test_demo")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToolTestDemoDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 名字
     */
    private String name;
    /**
     * 状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 类型
     *
     * 枚举 {@link OperateTypeEnum}
     */
    private Integer type;
    /**
     * 分类
     *
     * 枚举 {@link RedisKeyDefine.TimeoutTypeEnum}
     */
    private Integer category;
    /**
     * 备注
     */
    private String remark;

}

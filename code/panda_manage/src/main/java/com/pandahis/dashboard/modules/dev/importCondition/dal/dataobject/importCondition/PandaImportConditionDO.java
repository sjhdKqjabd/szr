package com.pandahis.dashboard.modules.dev.importCondition.dal.dataobject.importCondition;

import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import com.pandahis.dashboard.framework.mybatis.core.dataobject.BaseDO;

/**
 * 导医信息 DO
 *
 * @author 源码乐园
 */
@TableName("panda_import_condition")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PandaImportConditionDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 病情
     */
    private String conditionInfo;
    /**
     * 挂号科室
     */
    private String deptName;

    private String docName;


}

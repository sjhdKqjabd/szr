package com.pandahis.dashboard.modules.dev.patRecord.dal.dataobject.patRecord;

import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import com.pandahis.dashboard.framework.mybatis.core.dataobject.BaseDO;

/**
 * 就诊 DO
 *
 * @author 源码乐园
 */
@TableName("panda_pat_record")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PandaPatRecordDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 诊断结果
     */
    private String result;
    /**
     * 病情描述
     */
    private String descInfo;
    /**
     * 接诊医生名
     */
    private String docName;
    /**
     * 挂号科室
     */
    private String deptName;
    /**
     * 病人名称
     */
    private String petName;
    /**
     * 病人Id
     */
    private String petId;

}

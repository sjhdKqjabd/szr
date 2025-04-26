package com.pandahis.dashboard.modules.dev.regiterInfo.dal.dataobject.registerInfo;

import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import com.pandahis.dashboard.framework.mybatis.core.dataobject.BaseDO;

/**
 * 挂号表 DO
 *
 * @author 源码乐园
 */
@TableName("panda_register_info")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PandaRegisterInfoDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 就诊人
     */
    private String petName;
    /**
     * 就诊人Id
     */
    private String petId;
    /**
     * 科室名
     */
    private String deptName;
    /**
     * 医生名
     */
    private String docName;
    /**
     * 应缴金额
     */
    private String needMoney;
    /**
     * 交款金额
     */
    private String chargeMoney;
    /**
     * 找零
     */
    private String changeMoney;

}

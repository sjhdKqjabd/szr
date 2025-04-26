package com.pandahis.dashboard.modules.dev.regiterInfo.controller.registerInfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 挂号表 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class PandaRegisterInfoBaseVO {

    @ApiModelProperty(value = "就诊人")
    private String petName;

    @ApiModelProperty(value = "就诊人Id")
    private String petId;

    @ApiModelProperty(value = "科室名")
    private String deptName;

    @ApiModelProperty(value = "医生名")
    private String docName;

    @ApiModelProperty(value = "应缴金额")
    private String needMoney;

    @ApiModelProperty(value = "交款金额")
    private String chargeMoney;

    @ApiModelProperty(value = "找零")
    private String changeMoney;

}

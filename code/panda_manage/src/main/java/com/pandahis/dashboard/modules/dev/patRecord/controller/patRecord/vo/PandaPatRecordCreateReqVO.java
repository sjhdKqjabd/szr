package com.pandahis.dashboard.modules.dev.patRecord.controller.patRecord.vo;

import lombok.*;
import io.swagger.annotations.*;

@ApiModel("就诊创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PandaPatRecordCreateReqVO extends PandaPatRecordBaseVO {

    @ApiModelProperty(value = "病人Id")
    private String petId;

}

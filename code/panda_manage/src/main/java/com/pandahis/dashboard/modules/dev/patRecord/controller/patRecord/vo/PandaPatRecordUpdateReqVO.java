package com.pandahis.dashboard.modules.dev.patRecord.controller.patRecord.vo;

import lombok.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("就诊更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PandaPatRecordUpdateReqVO extends PandaPatRecordBaseVO {

    @ApiModelProperty(value = "id", required = true)
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "病人Id")
    private String petId;

}

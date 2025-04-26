package com.pandahis.dashboard.modules.dev.regiterInfo.controller.registerInfo.vo;

import lombok.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("挂号表更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PandaRegisterInfoUpdateReqVO extends PandaRegisterInfoBaseVO {

    @ApiModelProperty(value = "id", required = true)
    @NotNull(message = "id不能为空")
    private Long id;

}

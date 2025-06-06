package com.pandahis.dashboard.modules.system.controller.dict.vo.type;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@ApiModel("字典类型更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDictTypeUpdateReqVO extends SysDictTypeBaseVO {

    @ApiModelProperty(value = "字典类型编号", required = true, example = "1024")
    @NotNull(message = "字典类型编号不能为空")
    private Long id;

}

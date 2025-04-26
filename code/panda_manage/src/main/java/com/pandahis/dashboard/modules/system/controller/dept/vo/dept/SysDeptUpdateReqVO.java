package com.pandahis.dashboard.modules.system.controller.dept.vo.dept;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@ApiModel("科室更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDeptUpdateReqVO extends SysDeptBaseVO {

    @ApiModelProperty(value = "科室编号", required = true, example = "1024")
    @NotNull(message = "科室编号不能为空")
    private Long id;

}

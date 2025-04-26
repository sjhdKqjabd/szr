package com.pandahis.dashboard.modules.system.controller.dept.vo.dept;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("科室精简信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysDeptSimpleRespVO {

    @ApiModelProperty(value = "科室编号", required = true, example = "1024")
    private Long id;

    @ApiModelProperty(value = "科室名称", required = true, example = "教研")
    private String name;

    @ApiModelProperty(value = "父科室 ID", required = true, example = "1024")
    private Long parentId;

}

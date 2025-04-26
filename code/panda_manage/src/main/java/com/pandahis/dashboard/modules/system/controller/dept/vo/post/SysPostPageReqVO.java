package com.pandahis.dashboard.modules.system.controller.dept.vo.post;

import com.pandahis.dashboard.common.pojo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel("岗位分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysPostPageReqVO extends PageParam {

    @ApiModelProperty(value = "岗位名称", example = "教研", notes = "模糊匹配")
    private String name;

    @ApiModelProperty(value = "展示状态", example = "1", notes = "参见 SysCommonStatusEnum 枚举类")
    private Integer status;

}

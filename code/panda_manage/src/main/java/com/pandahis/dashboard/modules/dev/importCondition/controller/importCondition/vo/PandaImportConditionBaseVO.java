package com.pandahis.dashboard.modules.dev.importCondition.controller.importCondition.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 导医信息 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class PandaImportConditionBaseVO {

    @ApiModelProperty(value = "病情")
    private String conditionInfo;

    @ApiModelProperty(value = "挂号科室")
    private String deptName;


    private String docName;

}

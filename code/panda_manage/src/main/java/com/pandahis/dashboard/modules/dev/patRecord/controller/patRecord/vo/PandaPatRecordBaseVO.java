package com.pandahis.dashboard.modules.dev.patRecord.controller.patRecord.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 就诊 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class PandaPatRecordBaseVO {

    @ApiModelProperty(value = "诊断结果")
    private String result;

    @ApiModelProperty(value = "病情描述")
    private String descInfo;

    @ApiModelProperty(value = "接诊医生名")
    private String docName;

    @ApiModelProperty(value = "挂号科室")
    private String deptName;

    @ApiModelProperty(value = "病人名称")
    private String petName;

}

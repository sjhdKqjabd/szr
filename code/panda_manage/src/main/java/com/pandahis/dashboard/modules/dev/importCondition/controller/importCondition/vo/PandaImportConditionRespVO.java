package com.pandahis.dashboard.modules.dev.importCondition.controller.importCondition.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("导医信息 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PandaImportConditionRespVO extends PandaImportConditionBaseVO {

    @ApiModelProperty(value = "id", required = true)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}

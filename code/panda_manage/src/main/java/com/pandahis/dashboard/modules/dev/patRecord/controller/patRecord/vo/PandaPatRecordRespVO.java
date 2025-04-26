package com.pandahis.dashboard.modules.dev.patRecord.controller.patRecord.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("就诊 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PandaPatRecordRespVO extends PandaPatRecordBaseVO {

    @ApiModelProperty(value = "id", required = true)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}

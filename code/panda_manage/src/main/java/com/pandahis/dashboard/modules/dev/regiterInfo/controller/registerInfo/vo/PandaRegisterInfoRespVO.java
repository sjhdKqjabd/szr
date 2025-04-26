package com.pandahis.dashboard.modules.dev.regiterInfo.controller.registerInfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("挂号表 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PandaRegisterInfoRespVO extends PandaRegisterInfoBaseVO {

    @ApiModelProperty(value = "id", required = true)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}

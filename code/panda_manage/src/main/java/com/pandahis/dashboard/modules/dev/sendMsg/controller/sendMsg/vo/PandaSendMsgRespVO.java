package com.pandahis.dashboard.modules.dev.sendMsg.controller.sendMsg.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("发送消息表 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PandaSendMsgRespVO extends PandaSendMsgBaseVO {

    @ApiModelProperty(value = "id", required = true)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}

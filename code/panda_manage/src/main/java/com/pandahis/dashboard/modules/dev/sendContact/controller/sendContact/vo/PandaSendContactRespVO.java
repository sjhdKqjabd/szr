package com.pandahis.dashboard.modules.dev.sendContact.controller.sendContact.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("联系人表 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PandaSendContactRespVO extends PandaSendContactBaseVO {

    @ApiModelProperty(value = "id", required = true)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}

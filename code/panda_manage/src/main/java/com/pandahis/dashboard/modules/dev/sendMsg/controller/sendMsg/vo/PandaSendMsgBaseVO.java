package com.pandahis.dashboard.modules.dev.sendMsg.controller.sendMsg.vo;

import com.pandahis.dashboard.util.date.DateUtils;
import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
* 发送消息表 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class PandaSendMsgBaseVO {

    @ApiModelProperty(value = "消息类型")
    private String type;

    @ApiModelProperty(value = "消息本体")
    private String content;

    @ApiModelProperty(value = "消息状态")
    private String status;

    @ApiModelProperty(value = "发送时间")
    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date sendTime;

    @ApiModelProperty(value = "接收人")
    private String toContactId;

    @ApiModelProperty(value = "发送人")
    private String fromUserId;

}

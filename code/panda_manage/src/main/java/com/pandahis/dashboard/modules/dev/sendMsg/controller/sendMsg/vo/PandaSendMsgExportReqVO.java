package com.pandahis.dashboard.modules.dev.sendMsg.controller.sendMsg.vo;

import com.pandahis.dashboard.util.date.DateUtils;
import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

@ApiModel(value = "发送消息表 Excel 导出 Request VO", description = "参数和 PandaSendMsgPageReqVO 是一致的")
@Data
public class PandaSendMsgExportReqVO {

    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始创建时间")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;

    @ApiModelProperty(value = "消息类型")
    private String type;

    @ApiModelProperty(value = "消息本体")
    private String content;

    @ApiModelProperty(value = "消息状态")
    private String status;

    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始发送时间")
    private Date beginSendTime;

    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束发送时间")
    private Date endSendTime;

    @ApiModelProperty(value = "接收人")
    private String toContactId;

    @ApiModelProperty(value = "发送人")
    private String fromUserId;

}

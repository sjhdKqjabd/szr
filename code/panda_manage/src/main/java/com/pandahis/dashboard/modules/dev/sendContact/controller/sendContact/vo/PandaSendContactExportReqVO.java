package com.pandahis.dashboard.modules.dev.sendContact.controller.sendContact.vo;

import com.pandahis.dashboard.util.date.DateUtils;
import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

@ApiModel(value = "联系人表 Excel 导出 Request VO", description = "参数和 PandaSendContactPageReqVO 是一致的")
@Data
public class PandaSendContactExportReqVO {

    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始创建时间")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;

    @ApiModelProperty(value = "展示名")
    private String displayName;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "索引值")
    private String index;

    @ApiModelProperty(value = "未读值")
    private String unread;

    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始最后发送时间")
    private Date beginLastSendTime;

    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束最后发送时间")
    private Date endLastSendTime;

    @ApiModelProperty(value = "最后一次发送内容")
    private String lastContent;

}

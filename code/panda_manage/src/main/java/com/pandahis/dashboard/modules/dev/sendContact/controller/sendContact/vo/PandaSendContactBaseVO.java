package com.pandahis.dashboard.modules.dev.sendContact.controller.sendContact.vo;

import com.pandahis.dashboard.util.date.DateUtils;
import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
* 联系人表 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class PandaSendContactBaseVO {

    @ApiModelProperty(value = "展示名")
    private String displayName;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "索引值")
    private String index;

    @ApiModelProperty(value = "未读值")
    private String unread;

    @ApiModelProperty(value = "最后发送时间")
    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date lastSendTime;

    @ApiModelProperty(value = "最后一次发送内容")
    private String lastContent;

}

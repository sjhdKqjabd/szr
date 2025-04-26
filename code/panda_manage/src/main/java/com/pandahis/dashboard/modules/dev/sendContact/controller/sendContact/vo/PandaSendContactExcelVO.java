package com.pandahis.dashboard.modules.dev.sendContact.controller.sendContact.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 联系人表 Excel VO
 *
 * @author 源码乐园
 */
@Data
public class PandaSendContactExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("创建时间")
    private Date createTime;

    @ExcelProperty("展示名")
    private String displayName;

    @ExcelProperty("头像")
    private String avatar;

    @ExcelProperty("索引值")
    private String index;

    @ExcelProperty("未读值")
    private String unread;

    @ExcelProperty("最后发送时间")
    private Date lastSendTime;

    @ExcelProperty("最后一次发送内容")
    private String lastContent;

}

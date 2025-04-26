package com.pandahis.dashboard.modules.dev.sendMsg.controller.sendMsg.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 发送消息表 Excel VO
 *
 * @author 源码乐园
 */
@Data
public class PandaSendMsgExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("创建时间")
    private Date createTime;

    @ExcelProperty("消息类型")
    private String type;

    @ExcelProperty("消息本体")
    private String content;

    @ExcelProperty("消息状态")
    private String status;

    @ExcelProperty("发送时间")
    private Date sendTime;

    @ExcelProperty("接收人")
    private String toContactId;

    @ExcelProperty("发送人")
    private String fromUserId;

}

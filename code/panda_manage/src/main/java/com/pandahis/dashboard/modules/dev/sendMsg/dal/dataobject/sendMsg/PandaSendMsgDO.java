package com.pandahis.dashboard.modules.dev.sendMsg.dal.dataobject.sendMsg;

import com.pandahis.dashboard.modules.system.dal.dataobject.user.SysUserDO;
import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import com.pandahis.dashboard.framework.mybatis.core.dataobject.BaseDO;

/**
 * 发送消息表 DO
 *
 * @author 源码乐园
 */
@TableName("panda_send_msg")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PandaSendMsgDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 消息类型
     */
    private String type;
    /**
     * 消息本体
     */
    private String content;
    /**
     * 消息状态
     */
    private String status;
    /**
     * 发送时间
     */
    private Date sendTime;
    /**
     * 接收人
     */
    private String toContactId;
    /**
     * 发送人
     */
    private String fromUserId;

    /**
     * 已读状态
     */
    private String readed;


    @TableField(exist = false)
    private SysUserDO fromUser;


}

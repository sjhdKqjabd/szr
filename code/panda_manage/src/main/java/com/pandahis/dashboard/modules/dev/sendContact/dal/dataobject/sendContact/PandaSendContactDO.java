package com.pandahis.dashboard.modules.dev.sendContact.dal.dataobject.sendContact;

import com.pandahis.dashboard.modules.dev.sendMsg.dal.dataobject.sendMsg.PandaSendMsgDO;
import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import com.pandahis.dashboard.framework.mybatis.core.dataobject.BaseDO;

/**
 * 联系人表 DO
 *
 * @author 源码乐园
 */
@TableName("panda_send_contact")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PandaSendContactDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 展示名
     */
    private String displayName;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 索引值
     */
    private String index;
    /**
     * 未读值
     */
    private String unread;
    /**
     * 最后发送时间
     */
    private Date lastSendTime;
    /**
     * 最后一次发送内容
     */
    private String lastContent;


    @TableField(exist = false)
    private List<PandaSendMsgDO>  sendMsgDOS;

}

package com.pandahis.dashboard.modules.system.mq.message.permission;

import com.pandahis.dashboard.framework.redis.core.pubsub.ChannelMessage;
import lombok.Data;

/**
 * 菜单数据刷新 Message
 */
@Data
public class SysMenuRefreshMessage implements ChannelMessage {

    @Override
    public String getChannel() {
        return "system.menu.refresh";
    }

}

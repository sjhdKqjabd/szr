package com.pandahis.dashboard.modules.system.mq.message.dept;

import com.pandahis.dashboard.framework.redis.core.pubsub.ChannelMessage;
import lombok.Data;

/**
 * 科室数据刷新 Message
 */
@Data
public class SysDeptRefreshMessage implements ChannelMessage {

    @Override
    public String getChannel() {
        return "system.dept.refresh";
    }

}

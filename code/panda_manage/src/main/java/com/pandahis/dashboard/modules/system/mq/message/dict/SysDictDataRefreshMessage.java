package com.pandahis.dashboard.modules.system.mq.message.dict;

import com.pandahis.dashboard.framework.redis.core.pubsub.ChannelMessage;
import lombok.Data;

/**
 * 字典数据数据刷新 Message
 */
@Data
public class SysDictDataRefreshMessage implements ChannelMessage {

    @Override
    public String getChannel() {
        return "system.dict-data.refresh";
    }

}

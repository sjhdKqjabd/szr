package com.pandahis.dashboard.framework.redis.core.pubsub;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Redis Channel Message 接口
 *
 * @author 源码乐园
 */
public interface ChannelMessage {

    /**
     * 获得 Redis Channel
     *
     * @return Channel
     */
    @JsonIgnore // 避免序列化
    String getChannel();

}

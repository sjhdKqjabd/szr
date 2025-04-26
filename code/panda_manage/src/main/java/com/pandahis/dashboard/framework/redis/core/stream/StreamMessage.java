package com.pandahis.dashboard.framework.redis.core.stream;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Redis Stream Message 接口
 *
 * @author 源码乐园
 */
public interface StreamMessage {

    /**
     * 获得 Redis Stream Key
     *
     * @return Channel
     */
    @JsonIgnore // 避免序列化
    String getStreamKey();

}

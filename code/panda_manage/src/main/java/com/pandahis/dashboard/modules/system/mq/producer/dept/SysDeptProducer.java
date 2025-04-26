package com.pandahis.dashboard.modules.system.mq.producer.dept;

import com.pandahis.dashboard.framework.redis.core.util.RedisMessageUtils;
import com.pandahis.dashboard.modules.system.mq.message.dept.SysDeptRefreshMessage;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Dept 科室相关消息的 Producer
 */
@Component
public class SysDeptProducer {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 发送 {@link SysDeptRefreshMessage} 消息
     */
    public void sendDeptRefreshMessage() {
        SysDeptRefreshMessage message = new SysDeptRefreshMessage();
        RedisMessageUtils.sendChannelMessage(stringRedisTemplate, message);
    }

}

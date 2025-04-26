package com.pandahis.dashboard.modules.system.mq.consumer.dept;

import com.pandahis.dashboard.framework.redis.core.pubsub.AbstractChannelMessageListener;
import com.pandahis.dashboard.modules.system.mq.message.dept.SysDeptRefreshMessage;
import com.pandahis.dashboard.modules.system.service.dept.SysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 针对 {@link SysDeptRefreshMessage} 的消费者
 *
 * @author 源码乐园
 */
@Component
@Slf4j
public class SysDeptRefreshConsumer extends AbstractChannelMessageListener<SysDeptRefreshMessage> {

    @Resource
    private SysDeptService deptService;

    @Override
    public void onMessage(SysDeptRefreshMessage message) {
        log.info("[onMessage][收到 Dept 刷新消息]");
        deptService.initLocalCache();
    }

}

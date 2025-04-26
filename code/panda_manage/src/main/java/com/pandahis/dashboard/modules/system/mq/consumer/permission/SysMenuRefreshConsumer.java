package com.pandahis.dashboard.modules.system.mq.consumer.permission;

import com.pandahis.dashboard.framework.redis.core.pubsub.AbstractChannelMessageListener;
import com.pandahis.dashboard.modules.system.mq.message.permission.SysMenuRefreshMessage;
import com.pandahis.dashboard.modules.system.service.permission.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 针对 {@link SysMenuRefreshMessage} 的消费者
 *
 * @author 源码乐园
 */
@Component
@Slf4j
public class SysMenuRefreshConsumer extends AbstractChannelMessageListener<SysMenuRefreshMessage> {

    @Resource
    private SysMenuService menuService;

    @Override
    public void onMessage(SysMenuRefreshMessage message) {
        log.info("[onMessage][收到 Menu 刷新消息]");
        menuService.initLocalCache();
    }

}

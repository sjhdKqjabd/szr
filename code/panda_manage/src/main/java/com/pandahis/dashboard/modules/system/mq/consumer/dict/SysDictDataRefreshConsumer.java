package com.pandahis.dashboard.modules.system.mq.consumer.dict;

import com.pandahis.dashboard.framework.redis.core.pubsub.AbstractChannelMessageListener;
import com.pandahis.dashboard.modules.system.mq.message.dict.SysDictDataRefreshMessage;
import com.pandahis.dashboard.modules.system.service.dict.SysDictDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 针对 {@link SysDictDataRefreshMessage} 的消费者
 *
 * @author 源码乐园
 */
@Component
@Slf4j
public class SysDictDataRefreshConsumer extends AbstractChannelMessageListener<SysDictDataRefreshMessage> {

    @Resource
    private SysDictDataService dictDataService;

    @Override
    public void onMessage(SysDictDataRefreshMessage message) {
        log.info("[onMessage][收到 DictData 刷新消息]");
        dictDataService.initLocalCache();
    }

}

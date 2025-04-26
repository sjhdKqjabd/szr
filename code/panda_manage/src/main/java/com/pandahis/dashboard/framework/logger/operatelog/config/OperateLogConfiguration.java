package com.pandahis.dashboard.framework.logger.operatelog.config;

import com.pandahis.dashboard.framework.logger.operatelog.core.aop.OperateLogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OperateLogConfiguration {

    @Bean
    public OperateLogAspect operateLogAspect() {
        return new OperateLogAspect();
    }

}

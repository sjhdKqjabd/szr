package com.pandahis.dashboard.framework.idempotent.config;

import com.pandahis.dashboard.framework.idempotent.core.aop.IdempotentAspect;
import com.pandahis.dashboard.framework.idempotent.core.keyresolver.IdempotentKeyResolver;
import com.pandahis.dashboard.framework.idempotent.core.keyresolver.impl.DefaultIdempotentKeyResolver;
import com.pandahis.dashboard.framework.idempotent.core.redis.IdempotentRedisDAO;
import com.pandahis.dashboard.framework.idempotent.core.keyresolver.impl.ExpressionIdempotentKeyResolver;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class IdempotentConfiguration {

    @Bean
    public IdempotentAspect idempotentAspect(List<IdempotentKeyResolver> keyResolvers, IdempotentRedisDAO idempotentRedisDAO) {
        return new IdempotentAspect(keyResolvers, idempotentRedisDAO);
    }

    @Bean
    public IdempotentRedisDAO idempotentRedisDAO(StringRedisTemplate stringRedisTemplate) {
        return new IdempotentRedisDAO(stringRedisTemplate);
    }

    // ========== 各种 IdempotentKeyResolver Bean ==========

    @Bean
    public DefaultIdempotentKeyResolver defaultIdempotentKeyResolver() {
        return new DefaultIdempotentKeyResolver();
    }

    @Bean
    public ExpressionIdempotentKeyResolver expressionIdempotentKeyResolver() {
        return new ExpressionIdempotentKeyResolver();
    }

}

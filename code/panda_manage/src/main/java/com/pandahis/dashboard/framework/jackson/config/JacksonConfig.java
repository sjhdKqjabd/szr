package com.pandahis.dashboard.framework.jackson.config;

import com.pandahis.dashboard.framework.jackson.deser.LocalDateTimeDeserializer;
import com.pandahis.dashboard.framework.jackson.ser.LocalDateTimeSerializer;
import com.pandahis.dashboard.util.json.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class JacksonConfig {

    @Bean
    @SuppressWarnings("InstantiationOfUtilityClass")
    public JsonUtils jsonUtils(ObjectMapper objectMapper) {
        SimpleModule simpleModule = new SimpleModule();
        /*
         * 1. 新增Long类型序列化规则，数值超过2^53-1，在JS会出现精度丢失问题，因此Long自动序列化为字符串类型
         * 2. 新增LocalDateTime序列化、反序列化规则
         */
        simpleModule
//                .addSerializer(Long.class, ToStringSerializer.instance)
//                    .addSerializer(Long.TYPE, ToStringSerializer.instance)
                    .addSerializer(LocalDateTime.class, LocalDateTimeSerializer.INSTANCE)
                    .addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE);

        objectMapper.registerModules(simpleModule);

        JsonUtils.init(objectMapper);
        return new JsonUtils();
    }
}

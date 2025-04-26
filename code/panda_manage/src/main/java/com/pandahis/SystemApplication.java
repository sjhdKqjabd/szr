package com.pandahis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SystemApplication {




    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        restTemplate.setRequestFactory(factory);

        return restTemplate;
    }


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SystemApplication.class, args);
        ConfigurableEnvironment environment = run.getEnvironment();
        String property = environment.getProperty("server.port");
        System.out.println("http://loclahost:"+property);

    }

}

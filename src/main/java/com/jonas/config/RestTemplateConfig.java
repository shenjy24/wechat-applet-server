package com.jonas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author shenjy
 * @createTime 2022/6/24 17:36
 * @description WebConfig
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectionRequestTimeout(5000);
        factory.setConnectTimeout(10000);
        factory.setReadTimeout(5000);
        restTemplate.setRequestFactory(factory);
        return restTemplate;
    }
}

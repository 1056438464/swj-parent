package com.swj.spring.cloud.web.admin.ribbon.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class RestTemplateConfiguration {

    @Bean
    @LoadBalanced //负载均衡注释
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

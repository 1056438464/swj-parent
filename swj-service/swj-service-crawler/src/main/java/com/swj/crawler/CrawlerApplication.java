package com.swj.crawler;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.swj.crawler.mapper.*")
public class CrawlerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrawlerApplication.class);
    }
}

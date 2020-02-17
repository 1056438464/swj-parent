package com.swj.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.swj.crawler.fegin"})
public class SearchApplication {
  public static void main(String[] args) {
    SpringApplication.run(SearchApplication.class);
  }
}

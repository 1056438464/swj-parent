package com.swj.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableConfigServer
@EnableEurekaClient
public class ConfigCentreApplication {
  public static void main(String[] args) {
    SpringApplication.run(ConfigCentreApplication.class, args);
  }
}

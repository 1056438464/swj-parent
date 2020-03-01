package com.swj.zuul.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 队列配置
 *
 * <p>Title: RabbitMQConfiguration
 *
 * <p>Description:
 *
 * @author Lusifer
 * @version 1.0.0
 * @date 2018/5/1 22:39
 */
@Configuration
public class RabbitMQConfiguration {

  public static final String log_info = "topic.log.info";

  public static final String log_error = "topic.log.error";

  @Bean("loginfo")
  public Queue loginfo() {
    return new Queue(log_info);
  }

  @Bean("logerror")
  public Queue logerror() {
    return new Queue(log_error);
  }

  /**
   * 定义交换机
   *
   * @return
   */
  @Bean
  public TopicExchange exchange() {
    return new TopicExchange("topicExchange");
  }

  /** 绑定消息队列到交换机,路由key:topic.message */
  @Bean
  Binding bindingExchangeMessage(Queue loginfo, TopicExchange exchange) {

    return BindingBuilder.bind(loginfo).to(exchange).with("topic.log.info");
  }

  /** 绑定消息队列到交换机,路由key:topic.# */
  @Bean
  Binding bindingExchangeMessages(Queue logerror, TopicExchange exchange) {

    return BindingBuilder.bind(logerror).to(exchange).with("topic.log.error");
  }
}

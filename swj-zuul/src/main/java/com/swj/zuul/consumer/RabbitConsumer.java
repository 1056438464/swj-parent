package com.swj.zuul.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitConsumer {

  @RabbitListener(queues = "topic.log.info")
  public void process(String message) {
    if (message == null) {
      return;
    }
    System.out.println(message);
  }

  @RabbitListener(queues = "topic.log.error")
  public void process2(String message) {
    if (message == null) {
      return;
    }
    System.out.println(message);
  }
}

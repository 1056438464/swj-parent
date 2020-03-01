package com.swj.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LoginFilter extends ZuulFilter {
  @Autowired private AmqpTemplate amqpTemplate;

  /**
   * 配置过滤类型，有四种不同生命周期的过滤器类型 1. pre：路由之前 2. routing：路由之时 3. post：路由之后 4. error：发送错误调用
   *
   * @return
   */
  @Override
  public String filterType() {
    return "pre";
  }

  /**
   * 配置过滤的顺序
   *
   * @return
   */
  @Override
  public int filterOrder() {
    return 0;
  }

  /**
   * 配置是否需要过滤：true/需要，false/不需要
   *
   * @return
   */
  @Override
  public boolean shouldFilter() {
    return true;
  }

  /**
   * 过滤器的具体业务代码
   *
   * @return
   * @throws ZuulException
   */
  @Override
  public Object run() throws ZuulException {
    RequestContext context = RequestContext.getCurrentContext();
    HttpServletRequest request = context.getRequest();
    String info = request.getMethod() + ">>>>" + request.getRequestURL().toString();
    String token = request.getParameter("error");
    if (token == null) {
      amqpTemplate.convertAndSend("topicExchange", "topic.log.error", info);
    } else {
      amqpTemplate.convertAndSend("topicExchange", "topic.log.info", info);
    }
    return null;
  }
}

package com.swj.itoken.commom.web.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "itoken-service-redis")
public interface RedisCacheService {

    @RequestMapping(value = "put", method = RequestMethod.GET)
    public String put(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value,
                      @RequestParam(value = "seconds") int seconds);


    @RequestMapping(value = "get", method = RequestMethod.GET)
    public String get(@RequestParam(value = "key") String key);
}

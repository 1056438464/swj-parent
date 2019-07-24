package com.swj.itoken.service.redis.controller;

import com.swj.itoken.service.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    private static final String RESULT_OK = "ok";

    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "put", method = RequestMethod.GET)
    public String set(String key, String value, int seconds) {
        redisService.set(key, value, seconds);
        return RESULT_OK;
    }

    @RequestMapping(value = "find", method = RequestMethod.GET)
    public String find(String key) {
        String json = null;

        Object obj = redisService.get(key);
        if (obj != null) {
            json = (String) redisService.get(key);
        }

        return json;
    }
}

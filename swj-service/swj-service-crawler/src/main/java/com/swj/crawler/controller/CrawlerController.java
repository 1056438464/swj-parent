package com.swj.crawler.controller;


import com.swj.crawler.mapper.TbSpuMapper;
import com.swj.crawler.mapper.sharding.LogMapper;
import com.swj.crawler.model.Log;
import com.swj.crawler.model.TbSpu;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/CrawlerController")
public class CrawlerController {
    @Resource
    private TbSpuMapper tbSpuMapper;

    @Resource
    private LogMapper logMapper;

    @Value("${work}")
    private int work;

    @RequestMapping("/selectByPrimaryKey")
    public TbSpu selectByPrimaryKey() throws Exception {

        return tbSpuMapper.selectByPrimaryKey("10000000616301");
    }

    @RequestMapping("/returnText")
    public String returnText() throws Exception {
        return "returnText";
    }

    @RequestMapping("/getConfigValue")
    public int getConfigValue() throws Exception {
        return work;
    }


    @RequestMapping("/insertLog")
    public int insertLog() throws Exception {
        Log log = new Log();
        log.setUserId(16);
        return logMapper.insert(log);
    }
}

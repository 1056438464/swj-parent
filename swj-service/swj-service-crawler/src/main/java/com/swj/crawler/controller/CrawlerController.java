package com.swj.crawler.controller;

import com.swj.crawler.mapper.TbSpuMapper;
import com.swj.crawler.model.TbSpu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/CrawlerController")
public class CrawlerController {
    @Resource
    private TbSpuMapper tbSpuMapper;

    @RequestMapping("/selectByPrimaryKey")
    public TbSpu selectByPrimaryKey() throws Exception{
       return tbSpuMapper.selectByPrimaryKey("10000000616300");
    }
}

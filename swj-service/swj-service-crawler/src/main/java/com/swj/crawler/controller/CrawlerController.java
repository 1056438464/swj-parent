package com.swj.crawler.controller;

import com.swj.crawler.mapper.TbSpuMapper;
import com.swj.crawler.model.TbSpu;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/CrawlerController")
public class CrawlerController {
  @Resource private TbSpuMapper tbSpuMapper;

  @RequestMapping("/selectByPrimaryKey")
  public TbSpu selectByPrimaryKey() throws Exception {
    return tbSpuMapper.selectByPrimaryKey("10000000616300");
  }

  @RequestMapping("/returnText")
  public String returnText() throws Exception {
    return "returnText";
  }
}

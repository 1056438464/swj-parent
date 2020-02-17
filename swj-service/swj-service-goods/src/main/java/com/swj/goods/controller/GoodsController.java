package com.swj.goods.controller;

import com.swj.crawler.fegin.CrawlerFegin;
import com.swj.crawler.model.TbSpu;
import com.swj.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/GoodsController")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    @RequestMapping("/selectByPrimaryKey")
    public TbSpu selectByPrimaryKey() throws Exception{
        return goodsService.selectByPrimaryKey();
    }
}

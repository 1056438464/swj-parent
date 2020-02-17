package com.swj.goods.service.impl;

import com.swj.crawler.fegin.CrawlerFegin;
import com.swj.crawler.model.TbSpu;
import com.swj.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceimpl implements GoodsService{
    @Autowired
    private CrawlerFegin crawlerFegin;

    @Override
    public TbSpu selectByPrimaryKey() {
        return crawlerFegin.selectByPrimaryKey();
    }
}

package com.swj.crawler.fegin;

import com.swj.crawler.model.TbSpu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "crawler")
public interface CrawlerFegin {
    @RequestMapping("/CrawlerController/selectByPrimaryKey")
    public TbSpu selectByPrimaryKey();
}

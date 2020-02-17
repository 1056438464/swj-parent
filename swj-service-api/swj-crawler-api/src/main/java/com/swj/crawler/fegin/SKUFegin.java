package com.swj.crawler.fegin;

import com.swj.crawler.model.TbSku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "crawler")
public interface SKUFegin {
    @RequestMapping("/SkuController/selectAll")
    public List<TbSku> selectAll();
}

package com.swj.crawler.controller;

import com.swj.crawler.mapper.TbSkuMapper;
import com.swj.crawler.mapper.TbSpuMapper;
import com.swj.crawler.model.TbSku;
import com.swj.crawler.model.TbSpu;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/SkuController")
public class SkuController {
    @Resource
    private TbSkuMapper tbSkuMapper;

    @RequestMapping("/selectAll")
    public List<TbSku> selectAll() throws Exception{
        return tbSkuMapper.selectAll();
    }
}

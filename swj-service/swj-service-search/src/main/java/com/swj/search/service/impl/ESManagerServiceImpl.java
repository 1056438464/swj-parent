package com.swj.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.swj.crawler.fegin.SKUFegin;
import com.swj.crawler.model.TbSku;
import com.swj.search.mapper.ESManagerMapper;
import com.swj.search.model.SkuInfo;
import com.swj.search.service.ESManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ESManagerServiceImpl implements ESManagerService {

  @Autowired private ElasticsearchTemplate elasticsearchTemplate;

  @Autowired private SKUFegin skuFegin;

  @Autowired private ESManagerMapper esManagerMapper;

  // 创建索引库结构
  @Override
  public void createMappingAndIndex() {
    // 创建索引
    elasticsearchTemplate.createIndex(SkuInfo.class);
    // 创建映射
    elasticsearchTemplate.putMapping(SkuInfo.class);
  }

  @Override
  public void importAll() {
    // 查询sku集合
    List<TbSku> tbSkus = skuFegin.selectAll();
    String jsonTbSkus = JSON.toJSONString(tbSkus);
    List<SkuInfo> skuInfos = JSON.parseArray(jsonTbSkus, SkuInfo.class);
    for (SkuInfo skuInfo : skuInfos) {
      Map spaceMap = JSON.parseObject(skuInfo.getSpec(), Map.class);
      skuInfo.setSpecMap(spaceMap);
    }
    // 导入索引库
    esManagerMapper.saveAll(skuInfos);
  }

  @Override
  public void importDataBySpuId(String spuId) {}

  @Override
  public void delDataBySpuId(String spuId) {}
}

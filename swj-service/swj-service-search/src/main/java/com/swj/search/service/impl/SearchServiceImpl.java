package com.swj.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.swj.search.model.SkuInfo;
import com.swj.search.service.SearchService;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {
  @Autowired private ElasticsearchTemplate elasticsearchTemplate;

  @Override
  public Map search(Map<String, String> searchMap) {
    Map<String, Object> resultMap = new HashMap<>();
    if (searchMap != null) {
      // 构建查询条件
      NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
      BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

      // 按照关键字查询
      if (StringUtils.isNotEmpty(searchMap.get("keywords"))) {
        boolQuery.must(
            QueryBuilders.matchQuery("name", searchMap.get("keywords")).operator(Operator.AND));
      }

      // 按照品牌进行过滤查询
      if (StringUtils.isNotEmpty(searchMap.get("brand"))) {
        boolQuery.filter(QueryBuilders.termQuery("brandName", searchMap.get("brand")));
      }

      // 范围查询
      if (StringUtils.isNotEmpty(searchMap.get("price"))) {
        String[] prices = searchMap.get("price").split("-");
        if (prices.length == 2) {
          boolQuery.filter(QueryBuilders.rangeQuery("price").lte(prices[0]));
        }
        boolQuery.filter(QueryBuilders.rangeQuery("price").gte(prices[1]));
      }

      nativeSearchQueryBuilder.withQuery(boolQuery);

      // 开启查询
      /** 第一个参数: 条件构建对象 第二个参数: 查询操作实体类 第三个参数: 查询结果操作对象 */
      // 封装查询结果
      AggregatedPage<SkuInfo> aggregatedPage =
          elasticsearchTemplate.queryForPage(
              nativeSearchQueryBuilder.build(),
              SkuInfo.class,
              new SearchResultMapper() {
                @Override
                public <T> AggregatedPage<T> mapResults(
                    SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {

                  List list = new ArrayList();
                  // 获取查询结果集
                  SearchHits searchHits = searchResponse.getHits();
                  for (SearchHit searchHit : searchHits) {
                    if (searchHit != null) {
                      SkuInfo skuInfo = JSON.parseObject(searchHit.toString(), SkuInfo.class);
                      Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
                      if (highlightFields != null && highlightFields.size() > 0) {
                        // 替换数据
                        skuInfo.setName(highlightFields.get("name").getFragments()[0].toString());
                      }

                      list.add((T) skuInfo);
                    }
                  }

                  return new AggregatedPageImpl<T>(
                      list, pageable, searchHits.getTotalHits(), searchResponse.getAggregations());
                }

                @Nullable
                @Override
                public <T> T mapSearchHit(SearchHit searchHit, Class<T> aClass) {
                  return null;
                }
              });

      resultMap.put("rows", aggregatedPage.getContent());
    }

    return null;
  }
}

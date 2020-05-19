package com.swj.crawler.shardingutil;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    private static final Logger log = LoggerFactory.getLogger(CustomPreciseShardingAlgorithm.class);

    public CustomPreciseShardingAlgorithm() {
    }

    //availableTargerNames为表名，例如：t_order0, t_order1
    //确定insert表，同时处理 = 和 in 的规则
    //shardingValue.getValue() % availableTargetNames.size()即为配置的取余规则
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        log.info("availableTargetNames: {}", availableTargetNames);
        log.info("shardingValue: {}", shardingValue);
        for (String name : availableTargetNames) {
            if (name.endsWith(shardingValue.getValue() % availableTargetNames.size() + "")) {
                return name;
            }
        }
        return null;
    }
}

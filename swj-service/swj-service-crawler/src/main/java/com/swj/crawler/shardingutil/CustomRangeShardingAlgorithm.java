package com.swj.crawler.shardingutil;


import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class CustomRangeShardingAlgorithm implements RangeShardingAlgorithm<Long> {

    private static final Logger log = LoggerFactory.getLogger(CustomRangeShardingAlgorithm.class);

    public CustomRangeShardingAlgorithm() {
    }

    //处理范围查询规则，支持between and
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> shardingValue) {
        log.info("availableTargetNames: {}", availableTargetNames);
        log.info("shardingValue: {}", shardingValue);

        Collection<String> collect = new ArrayList<>();
        Range<Long> valueRange = shardingValue.getValueRange();

        if (valueRange.hasLowerBound() && valueRange.hasUpperBound()) {
            getCollect(availableTargetNames, collect, valueRange.lowerEndpoint(), valueRange.upperEndpoint());
            return collect;
        }
        return availableTargetNames;
    }

    private void getCollect(Collection<String> availableTargetNames, Collection<String> collect, Long lower, Long upper) {
        for (Long i = lower; i <= upper; i++) {
            for (String each : availableTargetNames) {
                if (each.endsWith(i % availableTargetNames.size() + "")) {
                    collect.add(each);
                }
            }
        }
    }
}

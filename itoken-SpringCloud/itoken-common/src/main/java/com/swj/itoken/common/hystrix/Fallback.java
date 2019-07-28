package com.swj.itoken.common.hystrix;

import com.swj.itoken.common.constants.HttpStatusConstants;
import com.swj.itoken.common.dto.BaseResult;
import com.swj.itoken.common.utils.MapperUtils;
import com.google.common.collect.Lists;

/**
 * 通用的熔断方法
 * <p>Title: Fallback</p>
 * <p>Description: </p>
 *
 * @author Lusifer
 * @version 1.0.0
 * @date 2018/8/8 10:57
 */
public class Fallback {
    /**
     * 502
     * @return
     */
    public static String badGateway() {
        BaseResult baseResult = BaseResult.notOk(Lists.newArrayList(
                new BaseResult.Error(
                        String.valueOf(HttpStatusConstants.BAD_GATEWAY.getStatus()),
                        HttpStatusConstants.BAD_GATEWAY.getContent())));
        try {
            return MapperUtils.obj2json(baseResult);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

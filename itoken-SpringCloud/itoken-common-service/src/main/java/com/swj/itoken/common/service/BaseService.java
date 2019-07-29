package com.swj.itoken.common.service;

import com.github.pagehelper.PageInfo;

public interface BaseService<T> {
    int insert(T t);
    int delete(T t);
    int update(T t);
    T selectOne(T t);

    PageInfo<T> page(int PageNum,int PageSize);
}

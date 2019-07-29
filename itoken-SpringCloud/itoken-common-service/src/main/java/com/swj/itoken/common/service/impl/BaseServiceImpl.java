package com.swj.itoken.common.service.impl;

import com.github.pagehelper.PageInfo;
import com.swj.itoken.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.myMapper;

@Service
@Transactional
public class BaseServiceImpl<T,D extends myMapper> implements BaseService<T> {
    @Autowired
    private D dao;

    @Override
    public int insert(T t) {
        return dao.insert(t);
    }

    @Override
    public int delete(T t) {
        return dao.delete(t);
    }

    @Override
    public int update(T t) {
        return update(t);
    }

    @Override
    public T selectOne(T t) {
        return (T) dao.selectOne(t);
    }

    @Override
    public PageInfo page(int PageNum, int PageSize) {
        return null;
    }
}

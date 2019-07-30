package com.swj.itoken.common.mapper;

import com.swj.itoken.common.domain.TbSysUser;
import com.swj.itoken.common.utils.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import tk.mybatis.mapper.myMapper;

@CacheNamespace(implementation = RedisCache.class)
public interface TbSysUserMapper extends myMapper<TbSysUser> {
}
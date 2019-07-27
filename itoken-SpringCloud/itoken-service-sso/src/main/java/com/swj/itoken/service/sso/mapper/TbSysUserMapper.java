package com.swj.itoken.service.sso.mapper;

import com.swj.itoken.common.domain.TbSysUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.myMapper;


@Repository
public interface TbSysUserMapper extends myMapper<TbSysUser> {
}
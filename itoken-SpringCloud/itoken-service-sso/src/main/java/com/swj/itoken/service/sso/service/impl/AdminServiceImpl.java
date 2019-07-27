package com.swj.itoken.service.sso.service.impl;

import com.swj.itoken.common.domain.TbSysUser;
import com.swj.itoken.service.sso.mapper.TbSysUserMapper;
import com.swj.itoken.service.sso.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private TbSysUserMapper tbSysUserMapper;

    @Override
    public void register(TbSysUser tbSysUser) throws Exception {
        tbSysUserMapper.insert(tbSysUser);
    }

    @Override
    public TbSysUser Login(String login_code, String plantPassWard) throws Exception {
        Example example = new Example(TbSysUser.class);
        example.createCriteria().andEqualTo("login_code",login_code);

        TbSysUser tbSysUser = tbSysUserMapper.selectOneByExample(example);

        String passward = DigestUtils.md5DigestAsHex(login_code.getBytes());

        if(passward.equals(tbSysUser.getPassword())){
            return tbSysUser;
        }
        return null;
    }
}

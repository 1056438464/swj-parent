package com.swj.itoken.service.admin.service.impl;

import com.swj.itoken.common.domain.TbSysUser;
import com.swj.itoken.common.service.impl.BaseServiceImpl;
import com.swj.itoken.service.admin.mapper.TbSysUserExtendMapper;
import com.swj.itoken.service.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

@Service
@Transactional
public class AdminServiceImpl extends BaseServiceImpl<TbSysUser, TbSysUserExtendMapper> implements AdminService<TbSysUser> {

    @Autowired
    private TbSysUserExtendMapper tbSysUserExtendMapper;

    @Override
    public void register(TbSysUser tbSysUser) throws Exception {
        tbSysUserExtendMapper.insert(tbSysUser);
    }

    @Override
    public TbSysUser Login(String login_code, String plantPassWard) throws Exception {
        Example example = new Example(TbSysUser.class);
        example.createCriteria().andEqualTo("login_code",login_code);

        TbSysUser tbSysUser = tbSysUserExtendMapper.selectOneByExample(example);

        String passward = DigestUtils.md5DigestAsHex(login_code.getBytes());

        if(passward.equals(tbSysUser.getPassword())){
            return tbSysUser;
        }
        return null;
    }
}

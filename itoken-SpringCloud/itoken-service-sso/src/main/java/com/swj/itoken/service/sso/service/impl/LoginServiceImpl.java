package com.swj.itoken.service.sso.service.impl;

import com.alibaba.fastjson.JSON;
import com.swj.itoken.common.domain.TbSysUser;
import com.swj.itoken.service.sso.mapper.TbSysUserMapper;
import com.swj.itoken.service.sso.service.LoginService;
import com.swj.itoken.service.sso.service.consumer.RedisCacheService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TbSysUserMapper tbSysUserMapper;

    @Autowired
    private RedisCacheService redisService;

    @Override
    public void register(TbSysUser tbSysUser) throws Exception {
        tbSysUserMapper.insert(tbSysUser);
    }

    @Override
    public TbSysUser Login(String login_code, String plantPassWard) throws Exception {
        TbSysUser tbSysUser = null;

        String json = redisService.get(login_code);
        // 缓存中没有数据，从数据库取数据
        if (json == null) {
            Example example = new Example(TbSysUser.class);
            example.createCriteria().andEqualTo("loginCode", login_code);

            tbSysUser = tbSysUserMapper.selectOneByExample(example);
            String password = DigestUtils.md5DigestAsHex(plantPassWard.getBytes());
            if (tbSysUser != null && password.equals(tbSysUser.getPassword())) {
                try {
                    redisService.put(login_code, JSON.toJSONString(tbSysUser), 60 * 60 * 24);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return tbSysUser;
            }

            else {
                return null;
            }
        }

        // 缓存中有数据
        else {
            try {
                tbSysUser = JSON.parseObject(json, TbSysUser.class);
            } catch (Exception e) {

            }
        }

        return tbSysUser;
    }
}

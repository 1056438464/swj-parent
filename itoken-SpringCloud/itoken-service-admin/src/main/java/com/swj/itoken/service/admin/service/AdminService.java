package com.swj.itoken.service.admin.service;

import com.swj.itoken.service.admin.domain.TbSysUser;

public interface AdminService {
    /**
     * 用户注册
     * @param tbSysUser
     * @throws Exception
     */
    public void register(TbSysUser tbSysUser)throws Exception;

    /**
     * 登录
     * @param login_code
     * @param plantPassWard
     * @return
     * @throws Exception
     */
    public TbSysUser Login(String login_code ,String plantPassWard)throws Exception;
}
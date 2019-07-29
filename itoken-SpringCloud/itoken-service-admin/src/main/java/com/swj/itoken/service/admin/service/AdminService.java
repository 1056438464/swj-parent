package com.swj.itoken.service.admin.service;


import com.swj.itoken.common.domain.TbSysUser;
import com.swj.itoken.common.service.BaseService;

public interface AdminService<T> extends BaseService<T>{
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

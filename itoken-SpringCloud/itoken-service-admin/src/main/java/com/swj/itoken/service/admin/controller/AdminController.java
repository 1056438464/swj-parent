package com.swj.itoken.service.admin.controller;


import com.swj.itoken.common.domain.TbSysUser;
import com.swj.itoken.service.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/login")
    public TbSysUser login(String login_code, String plantPassWard ) throws Exception{
        return adminService.Login(login_code, plantPassWard);
    }
}


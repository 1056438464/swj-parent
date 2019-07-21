package com.swj.itoken.web.admin.service;

import com.swj.itoken.web.admin.service.fallback.AdminServiceFallBack;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Repository
@FeignClient(value = "itoken-service-admin",fallback = AdminServiceFallBack.class)
public interface AdminService {

    @RequestMapping(value = "login")
    public String login(@RequestParam(value = "login_code") String login_code,@RequestParam(value = "plantPassWard") String plantPassWard);
}

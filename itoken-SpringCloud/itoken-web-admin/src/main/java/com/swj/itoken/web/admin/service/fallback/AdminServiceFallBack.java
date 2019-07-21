package com.swj.itoken.web.admin.service.fallback;

import com.swj.itoken.web.admin.service.AdminService;
import org.springframework.stereotype.Component;

@Component
public class AdminServiceFallBack implements AdminService {
    @Override
    public String login(String login_code, String plantPassWard) {
        return "502";
    }
}

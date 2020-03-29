package com.usongon.pocketmedical.controller;

import com.usongon.pocketmedical.bean.ResponseResult;
import com.usongon.pocketmedical.bean.param.LoginParams;
import com.usongon.pocketmedical.framework.annotation.Authorize;
import com.usongon.pocketmedical.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhangdehua
 * @date 2019-11-11
 */
@RestController
public class LoginController {
    @Resource
    private LoginService loginService;

    @Authorize(login = false)
    @PostMapping("/doctor/login")
    public Object adminLogin(LoginParams params){
        return ResponseResult.success(loginService.doctorLogin(params));
    }

    @Authorize(login = false)
    @PostMapping("/patient/login")
    public Object userLogin(LoginParams params){
        return ResponseResult.success(loginService.patientLogin(params));
    }

    @Authorize
    @PostMapping("/logout")
    public Object logout(@RequestHeader(name = "token") String token){
        loginService.logout(token);
        return ResponseResult.success();
    }

}

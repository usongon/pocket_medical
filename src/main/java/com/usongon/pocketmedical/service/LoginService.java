package com.usongon.pocketmedical.service;

import com.usongon.pocketmedical.bean.param.LoginParams;
import com.usongon.pocketmedical.bean.result.LoginResult;

/**
 * @author zhangdehua
 * @date 2019-11-12
 */
public interface LoginService {
    LoginResult doctorLogin(LoginParams params);
    LoginResult patientLogin(LoginParams params);
    LoginResult adminLogin(LoginParams params);
    void logout(String key);
}

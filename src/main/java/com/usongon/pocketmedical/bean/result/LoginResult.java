package com.usongon.pocketmedical.bean.result;

import com.usongon.pocketmedical.enums.ELoginType;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yxp
 * @date 2019-11-12
 */
@Data
@AllArgsConstructor
public class LoginResult {
    private ELoginType loginType;
    private String token;
    private String name;
    private String role;
    private String mobile;
}

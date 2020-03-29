package com.usongon.pocketmedical.bean.session;

import com.usongon.pocketmedical.enums.ELoginType;
import lombok.Data;

@Data
public class UserSession extends LoginSession {
    private String userId;

    @Override
    public String loginId() {
        return userId;
    }

    @Override
    public ELoginType loginType() {
        return ELoginType.User;
    }
}

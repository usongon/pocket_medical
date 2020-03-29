package com.usongon.pocketmedical.bean.session;

import com.usongon.pocketmedical.enums.ELoginType;
import lombok.Data;

@Data
public class AdminSession extends LoginSession {
    private String adminId;

    @Override
    public String loginId() {
        return adminId;
    }

    @Override
    public ELoginType loginType() {
        return ELoginType.Admin;
    }
}

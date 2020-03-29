package com.usongon.pocketmedical.bean.session;

import com.usongon.pocketmedical.enums.ELoginType;
import lombok.Data;

@Data
public class DoctorSession extends LoginSession {
    private String doctorId;

    @Override
    public String loginId() {
        return doctorId;
    }

    @Override
    public ELoginType loginType() {
        return ELoginType.Admin;
    }
}

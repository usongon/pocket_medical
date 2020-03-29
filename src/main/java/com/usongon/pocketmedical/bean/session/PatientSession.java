package com.usongon.pocketmedical.bean.session;

import com.usongon.pocketmedical.enums.ELoginType;
import lombok.Data;

@Data
public class PatientSession extends LoginSession {
    private String patientId;

    @Override
    public String loginId() {
        return patientId;
    }

    @Override
    public ELoginType loginType() {
        return ELoginType.Patient;
    }
}

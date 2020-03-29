package com.usongon.pocketmedical.service.impl;

import com.usongon.pocketmedical.bean.entity.Doctor;
import com.usongon.pocketmedical.bean.entity.Patient;
import com.usongon.pocketmedical.bean.param.LoginParams;
import com.usongon.pocketmedical.bean.result.LoginResult;
import com.usongon.pocketmedical.bean.session.DoctorSession;
import com.usongon.pocketmedical.bean.session.PatientSession;
import com.usongon.pocketmedical.common.utils.PasswordUtil;
import com.usongon.pocketmedical.common.utils.UuidUtil;
import com.usongon.pocketmedical.enums.EDoctorAndPatientState;
import com.usongon.pocketmedical.enums.ELoginType;
import com.usongon.pocketmedical.enums.EResponseCode;
import com.usongon.pocketmedical.framework.annotation.Authorize;
import com.usongon.pocketmedical.framework.exception.BusinessException;
import com.usongon.pocketmedical.redis.SessionRedis;
import com.usongon.pocketmedical.service.DoctorService;
import com.usongon.pocketmedical.service.LoginService;
import com.usongon.pocketmedical.service.PatientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author zhangdehua
 * @date 2019-11-12
 */
@Log4j2
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private DoctorService doctorService;
    @Resource
    private PatientService patientService;
    @Resource
    private SessionRedis sessionRedis;
    @Override
    @Authorize(login = false)
    public LoginResult doctorLogin(LoginParams params) {
        Doctor doctor = doctorService.selectByDoctorMobile(params.getMobile());
        if (doctor == null){
            throw new BusinessException(EResponseCode.BizError, "账号或密码错误", "");
        }
        if (!PasswordUtil.match(params.getPassword(), doctor.getDocPassword())){
            throw new BusinessException(EResponseCode.BizError, "账号或密码错误", "");
        }
        if (doctor.getDocState().equals(EDoctorAndPatientState.Del.toString())){
            throw new BusinessException(EResponseCode.BizError, "账号或密码错误", "");
        }
        if (!doctor.getDocState().equals(EDoctorAndPatientState.On.toString())){
            throw new BusinessException(EResponseCode.BizError, "您的账号未在启用状态", "");
        }
        DoctorSession session = new DoctorSession();
        session.setDoctorId(doctor.getDocId());
        String uuid = "doctor:" + UuidUtil.randomUUID();
        sessionRedis.setSession(uuid, session);
        return new LoginResult(ELoginType.Doctor, uuid, doctor.getDocName(), "doctor");
    }

    @Override
    public LoginResult patientLogin(LoginParams params) {
        Patient patient = patientService.selectByPatientMobile(params.getMobile());
        if (patient == null){
            throw new BusinessException(EResponseCode.BizError, "账号或密码错误", "");
        }
        if (!PasswordUtil.match(params.getPassword(), patient.getPatientPassword())){
            throw new BusinessException(EResponseCode.BizError, "账号或密码错误", "");
        }
        if (patient.getPatientState().equals(EDoctorAndPatientState.Del.toString())){
            throw new BusinessException(EResponseCode.BizError, "账号或密码错误", "");
        }
        if (!patient.getPatientState().equals(EDoctorAndPatientState.On.toString())){
            throw new BusinessException(EResponseCode.BizError, "您的账号未在启用状态", "");
        }
        PatientSession session = new PatientSession();
        session.setPatientId(patient.getPatientId());
        String uuid = "patient:" + UuidUtil.randomUUID();
        sessionRedis.setSession(uuid, session);
        return new LoginResult(ELoginType.Patient, uuid, patient.getPatientName(), "Patient");
    }

    @Override
    public void logout(String key) {
        if (!StringUtils.isEmpty(key)){
            sessionRedis.delete(key);
        }
    }
}

package com.usongon.pocketmedical.controller;

import com.usongon.pocketmedical.bean.ResponseResult;
import com.usongon.pocketmedical.bean.param.PatientRegisterParams;
import com.usongon.pocketmedical.enums.EResponseCode;
import com.usongon.pocketmedical.framework.annotation.Authorize;
import com.usongon.pocketmedical.framework.exception.BusinessException;
import com.usongon.pocketmedical.service.DoctorService;
import com.usongon.pocketmedical.service.PatientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhangdehua
 * @date 2020-03-29
 */
@RestController
@Authorize
public class PatientController {
    @Resource
    private PatientService patientService;

    @Authorize(login = false)
    @PostMapping("/patient/register")
    public Object registerDoc(PatientRegisterParams params){
        final String F = "女";
        final String M = "男";
        if (!F.equals(params.getPatientSex()) && !M.equals(params.getPatientSex())){
            throw new BusinessException(EResponseCode.BizError,"性别输入有误", "");
        }
        patientService.insert(params);
        return ResponseResult.success();
    }
}

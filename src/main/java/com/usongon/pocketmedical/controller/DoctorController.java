package com.usongon.pocketmedical.controller;

import com.usongon.pocketmedical.bean.ResponseResult;
import com.usongon.pocketmedical.bean.param.DoctorRegisterParams;
import com.usongon.pocketmedical.common.utils.PasswordUtil;
import com.usongon.pocketmedical.enums.EResponseCode;
import com.usongon.pocketmedical.framework.exception.BusinessException;
import com.usongon.pocketmedical.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhangdehua
 * @date 2020-03-29
 */
@RestController
public class DoctorController {
    @Resource
    private DoctorService doctorService;

    @PostMapping("/doctor/register")
    public Object registerDoc(DoctorRegisterParams params){
        doctorService.insert(params);
        return ResponseResult.success();
    }
}

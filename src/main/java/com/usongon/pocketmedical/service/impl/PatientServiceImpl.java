package com.usongon.pocketmedical.service.impl;

import com.usongon.pocketmedical.bean.param.PatientRegisterParams;
import com.usongon.pocketmedical.common.utils.PasswordUtil;
import com.usongon.pocketmedical.common.utils.UuidUtil;
import com.usongon.pocketmedical.enums.EResponseCode;
import com.usongon.pocketmedical.framework.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.usongon.pocketmedical.dao.PatientMapper;
import com.usongon.pocketmedical.bean.entity.Patient;
import com.usongon.pocketmedical.service.PatientService;

/**
 * @author zhangdehua
 * @date 2020-03-29
 */
@Service
public class PatientServiceImpl implements PatientService {

    @Resource
    private PatientMapper patientMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return patientMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PatientRegisterParams params) {
        if (patientMapper.selectByPatientMobile(params.getPatientMobile()) != null){
            throw new BusinessException(EResponseCode.BizError, "手机号重复", "");
        }
        params.setPatientPassword(PasswordUtil.encode(params.getPatientPassword()));
        Patient record = new Patient();
        BeanUtils.copyProperties(params, record);
        record.setPatientId(UuidUtil.randomUUID());
        return patientMapper.insert(record);
    }

    @Override
    public int insertSelective(Patient record) {
        return patientMapper.insertSelective(record);
    }

    @Override
    public Patient selectByPrimaryKey(Integer id) {
        return patientMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Patient record) {
        return patientMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Patient record) {
        return patientMapper.updateByPrimaryKey(record);
    }

}




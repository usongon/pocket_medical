package com.usongon.pocketmedical.service.impl;

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
    public int insert(Patient record) {
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




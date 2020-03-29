package com.usongon.pocketmedical.service;

import com.usongon.pocketmedical.bean.entity.Patient;
import com.usongon.pocketmedical.bean.param.PatientRegisterParams;

/**
 * @author zhangdehua
 * @date 2020-03-29
 */
public interface PatientService {


    int deleteByPrimaryKey(Integer id);

    int insert(PatientRegisterParams record);

    int insertSelective(Patient record);

    Patient selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Patient record);

    int updateByPrimaryKey(Patient record);

}




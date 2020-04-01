package com.usongon.pocketmedical.service;

import com.usongon.pocketmedical.bean.entity.Patient;
import com.usongon.pocketmedical.bean.param.PatientRegisterParams;
import com.usongon.pocketmedical.bean.param.PatientSelectParams;
import com.usongon.pocketmedical.bean.result.PatientResult;

import java.util.List;

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

    Patient selectByPatientMobile(String mobile);

    void updatePatientStateByPatientIdAndPatientState(String patientId,String updatedPatientState);

    List<PatientResult> selectByAllExceptId(PatientSelectParams params);

}




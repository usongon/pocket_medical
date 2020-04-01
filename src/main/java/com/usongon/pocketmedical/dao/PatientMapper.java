package com.usongon.pocketmedical.dao;
import java.util.List;

import com.usongon.pocketmedical.bean.entity.Patient;
import com.usongon.pocketmedical.bean.param.PatientSelectParams;
import com.usongon.pocketmedical.bean.result.PatientResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhangdehua
 * @date 2020-03-29
 */
@Mapper
public interface PatientMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Patient record);

    int insertSelective(Patient record);

    Patient selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Patient record);

    int updateByPrimaryKey(Patient record);

    Patient selectByPatientMobile(@Param("patientMobile") String patientMobile);

    void updatePatientStateByPatientIdAndPatientState(@Param("updatedPatientState")String updatedPatientState,@Param("patientId")String patientId);

	List<PatientResult> selectByAllExceptId(PatientSelectParams params);

}
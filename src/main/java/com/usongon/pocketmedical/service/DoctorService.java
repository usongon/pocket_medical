package com.usongon.pocketmedical.service;

import com.usongon.pocketmedical.bean.entity.Doctor;
import com.usongon.pocketmedical.bean.param.DoctorRegisterParams;
import com.usongon.pocketmedical.bean.param.DoctorSelectParams;
import com.usongon.pocketmedical.bean.result.DoctorResult;

import java.util.List;

/**
 * @author zhangdehua
 * @date 2020-03-29
 */
public interface DoctorService {

    int insert(DoctorRegisterParams params);

    int insertSelective(Doctor record);

    Doctor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Doctor record);

    int updateByPrimaryKey(Doctor record);

    Doctor selectByDoctorMobile(String mobile);

    void updateDecDocStateByDocId(String docId, String deductedDocState);

    List<Doctor> selectAllByDocState(String docState);

    List<Doctor> selectByAll(Doctor doctor);

    List<DoctorResult> getDoctorList(DoctorSelectParams params);

    Doctor selectByDocIdAndDocState(String docId);

    List<DoctorResult> getDoctorListByDepartmentId(String departmentId);
}


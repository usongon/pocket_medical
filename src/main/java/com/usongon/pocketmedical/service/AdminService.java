package com.usongon.pocketmedical.service;

import com.usongon.pocketmedical.bean.entity.Admin;
import com.usongon.pocketmedical.bean.entity.Doctor;
import com.usongon.pocketmedical.bean.param.AdminInsertParams;
import com.usongon.pocketmedical.bean.param.DoctorSelectParams;
import com.usongon.pocketmedical.bean.param.PatientSelectParams;
import com.usongon.pocketmedical.bean.result.DepartmentResult;
import com.usongon.pocketmedical.bean.result.DoctorResult;
import com.usongon.pocketmedical.bean.result.PatientResult;
import com.usongon.pocketmedical.enums.EDoctorAndPatientState;

import java.util.List;
    /**
 *
 * @author yxp
 * @date 2020-03-31
 */
public interface AdminService{


    int insert(AdminInsertParams params);

    int insertSelective(Admin record);

    Admin selectByAdminMobileAndAdminState(String adminMobile);

    int updateByAdminId(Admin updated,String adminId);

    List<Admin> selectByAdminIdAndAdminState(String adminId);

    void changeDocState(String docId, EDoctorAndPatientState state);

    List<DoctorResult> getDoctorList(DoctorSelectParams params);

    List<PatientResult> getPatientList(PatientSelectParams params);

    void changePatientState(String patientId, EDoctorAndPatientState state);

    void insertDepartment(String departmentName);

    List<DepartmentResult> getDepartmenrList();
    }

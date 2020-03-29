package com.usongon.pocketmedical.service;

import com.usongon.pocketmedical.bean.entity.Doctor;
import com.usongon.pocketmedical.bean.param.DoctorRegisterParams;

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

}


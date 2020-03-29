package com.usongon.pocketmedical.dao;

import com.usongon.pocketmedical.bean.entity.Patient;

/**
 * @author zhangdehua
 * @date 2020-03-29
 */
public interface PatientMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Patient record);

    int insertSelective(Patient record);

    Patient selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Patient record);

    int updateByPrimaryKey(Patient record);
}
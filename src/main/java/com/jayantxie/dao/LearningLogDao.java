package com.jayantxie.dao;

import com.jayantxie.model.LearningLog;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningLogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(LearningLog record);

    int insertSelective(LearningLog record);

    LearningLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LearningLog record);

    int updateByPrimaryKeyWithBLOBs(LearningLog record);

    int updateByPrimaryKey(LearningLog record);
}
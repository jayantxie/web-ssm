package com.jayantxie.dao;

import com.jayantxie.model.EvaluationData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationDataDao {
    int deleteByPrimaryKey(Integer id);

    int insert(EvaluationData record);

    int insertSelective(EvaluationData record);

    EvaluationData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EvaluationData record);

    int updateByPrimaryKeyWithBLOBs(EvaluationData record);

    int updateByPrimaryKey(EvaluationData record);

    List<EvaluationData> selectTenByPrimaryKey(String userName, Integer number);

    Integer selectLatestOne(String userName);
}
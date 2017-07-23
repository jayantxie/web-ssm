package com.jayantxie.dao;

import com.jayantxie.model.DistractionTime;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistractionTimeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(DistractionTime record);

    int insertSelective(DistractionTime record);

    DistractionTime selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DistractionTime record);

    int updateByPrimaryKey(DistractionTime record);

    List<DistractionTime> selectListByEvaId(Integer evaId);
}
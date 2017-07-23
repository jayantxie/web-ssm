package com.jayantxie.dao;

import com.jayantxie.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    int deleteByPrimaryKey(String name);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String name);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
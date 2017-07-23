package com.jayantxie.dao;

import com.jayantxie.model.Friend;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Friend record);

    int insertSelective(Friend record);

    Friend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Friend record);

    int updateByPrimaryKey(Friend record);

    List<Friend> selectAddedFriendByName(String name);

    List<Friend> selectAddingFriendByName(String name);

    Friend selectByFriendObject(Friend friend);
}
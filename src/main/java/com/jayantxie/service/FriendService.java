package com.jayantxie.service;

import com.jayantxie.model.Friend;
import com.jayantxie.model.User;

import java.util.List;

/**
 * Created by 天亮就出发 on 2017/3/12.
 */
public interface FriendService {

    public boolean requestAddFriend(Friend friend);

    public boolean deleteFriend(Friend friend);

    public int friendState(Friend friend);

    public boolean doneAddFriend(Friend friend);

    public List<User> queryAllFriend(String name,Integer state);
}

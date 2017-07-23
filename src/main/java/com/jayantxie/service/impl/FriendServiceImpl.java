package com.jayantxie.service.impl;

import com.jayantxie.dao.FriendDao;
import com.jayantxie.dao.UserDao;
import com.jayantxie.model.Friend;
import com.jayantxie.model.User;
import com.jayantxie.service.FriendService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 天亮就出发 on 2017/3/12.
 */

@Service("friendService")
@Transactional(rollbackFor = Exception.class)
public class FriendServiceImpl implements FriendService {
    @Resource
    private FriendDao friendDao;
    @Resource
    private UserDao userDao;

    public boolean requestAddFriend(Friend friend){
        friend.setState(1);
        Friend newFriend = this.friendDao.selectByFriendObject(friend);
        if(newFriend == null){
            this.friendDao.insertSelective(friend);
            return true;
        }
        else
            return false;
    }

    public boolean deleteFriend(Friend friend){
        Friend newFriend = this.friendDao.selectByFriendObject(friend);
        if(newFriend == null)
            return false;
        else{
            this.friendDao.deleteByPrimaryKey(newFriend.getId());
            return true;
        }
    }

    public int friendState(Friend friend){
        Friend newFriend = this.friendDao.selectByFriendObject(friend);
        if(newFriend == null)
            return 0;
        else
            return newFriend.getState();
    }

    public boolean doneAddFriend(Friend friend){
        Friend newFriend = this.friendDao.selectByFriendObject(friend);
        newFriend.setState(2);
        return (this.friendDao.updateByPrimaryKey(newFriend) == 1);
    }

    public List<User> queryAllFriend(String name,Integer state){
        List<User> list = new ArrayList<User>();
        List<Friend> flist;
        if(state == 1){
            flist = this.friendDao.selectAddingFriendByName(name);
        }else if(state == 2){
            flist = this.friendDao.selectAddedFriendByName(name);
        }else
            return null;
        if(flist==null)
            return null;
        for(Friend friend:flist){
            if(friend.getUserName().equals(name)) {
                User user = this.userDao.selectByPrimaryKey(friend.getFriendName());
                user.setToken("");
                user.setPassword("");
                list.add(user);
            }
            else {
                User user = this.userDao.selectByPrimaryKey(friend.getUserName());
                user.setToken("");
                user.setPassword("");
                list.add(user);
            }
        }
        return list;
    }
}

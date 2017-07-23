package com.jayantxie.service.impl;

import com.jayantxie.dao.UserDao;
import com.jayantxie.model.User;
import com.jayantxie.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;


@Service("userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService{

    @Resource
    private UserDao userDao;

    private String getRandomString() { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 5; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public boolean register(User user){
        if(this.userDao.selectByPrimaryKey(user.getName()) == null) {
            this.userDao.insertSelective(user);
            return true;
        }
        else return false;
    }


    public User login(User user){
        User newUser = this.userDao.selectByPrimaryKey(user.getName());
        if(newUser == null)
            return null;
        if(newUser.getPassword().equals(user.getPassword())) {
            String token = System.currentTimeMillis() + getRandomString();
            newUser.setToken(token);
            System.out.println(token);
            this.userDao.updateByPrimaryKey(newUser);
            //置空密码
            newUser.setPassword("");
            return newUser;
        }
        else return null;
    }

    public User query(String name){
        User user = this.userDao.selectByPrimaryKey(name);
        if(user == null)
            return null;
        user.setPassword("");
        user.setToken("");
        return user;
    }

    public boolean update(User user){
        if(this.userDao.updateByPrimaryKeySelective(user) == 1)
            return true;
        else
            return false;
    }

}
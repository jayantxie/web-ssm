package com.jayantxie.service;

import com.jayantxie.model.User;


public interface UserService {

    public boolean register(User user);

    public User login(User user);

    public User query(String name);

    public boolean update(User user);
}

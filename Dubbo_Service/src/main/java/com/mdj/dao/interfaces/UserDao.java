package com.mdj.dao.interfaces;

import com.mdj.bean.User;

public interface UserDao {
    public User getUser(String userName ,String passWord) throws Exception;
    
    public  void addUser(User user) throws Exception;
}

package com.mdj.dao.impl;

import org.springframework.stereotype.Component;

import com.mdj.bean.User;
import com.mdj.dao.interfaces.UserDao;
//@Component
public class UserDaoImpl implements UserDao{
    @Override
    public User getUser(String userName, String passWord) throws Exception{
    	throw new Exception("getUser��Ӧ����������");
    }
    
    @Override
    public void addUser(User user) throws Exception {
    	throw new Exception("addUser��Ӧ����������");
    }
}

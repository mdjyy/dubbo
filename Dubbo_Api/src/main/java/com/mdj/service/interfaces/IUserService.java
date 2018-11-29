package com.mdj.service.interfaces;

import java.util.Map;

import com.mdj.bean.User;

public interface IUserService {
	
    public User getUser(String userName,String passWord) throws Exception;

    public Map addUser(User user) throws Exception;
}

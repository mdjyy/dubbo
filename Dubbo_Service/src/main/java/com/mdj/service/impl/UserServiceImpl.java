package com.mdj.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.mdj.bean.User;
import com.mdj.dao.interfaces.UserDao;
import com.mdj.service.interfaces.IUserService;

@Service(interfaceName="com.mdj.service.interfaces.IUserService")
public class UserServiceImpl implements IUserService{

	private static Logger log = Logger.getLogger(UserServiceImpl.class);

	@Autowired(required=false)
	private UserDao userDao;

	@Transactional
	public User getUser(String userName,String passWord) throws Exception {

		log.info("-----接口报文------userName:"+userName+",passWord:"+passWord);

		if(userName==null||passWord==null){
			throw new Exception("用户名或密码为空");
		}

		try {
			return userDao.getUser(userName,passWord);    		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional
	public Map addUser(User user) throws Exception{
		
        Map map = new HashMap();
        
        map.put("isSuccess", false);
        
        log.info("-----接口报文------user:"+user);

		if(user==null){
            throw new Exception("用户为空");
		}
		
		try {
	        userDao.addUser(user);
	        map.put("isSuccess", true);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return map;
	}
}

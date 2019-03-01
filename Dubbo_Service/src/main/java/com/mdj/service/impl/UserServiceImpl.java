package com.mdj.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.mdj.bean.User;
import com.mdj.cache.CacheClient;
import com.mdj.dao.interfaces.UserDao;
import com.mdj.service.interfaces.IUserService;

@Service(interfaceName="com.mdj.service.interfaces.IUserService")
@org.springframework.stereotype.Service(value="userService")
public class UserServiceImpl implements IUserService{

	private static Logger log = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Transactional
	public User getUser(String userName,String passWord) throws Exception {
		
		log.info("-----�ӿڱ���------userName:"+userName+",passWord:"+passWord);

		if(userName==null||passWord==null){
			throw new Exception("�û���������Ϊ��");
		}
		User user = null;
		try {
			//�ӻ����ж�ȡ
			user = CacheClient.getObject(userName, User.class);
			if(user==null){
				log.info("�����ݿ��ж�ȡ");
				user =  userDao.getUser(userName,passWord);
				CacheClient.putObject(userName,user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return user;
	}

	@Transactional
	public Map addUser(User user) throws Exception{
		
        Map map = new HashMap();
        
        map.put("isSuccess", false);
        
        log.info("-----�ӿڱ���------user:"+user);

		if(user==null){
            throw new Exception("�û�Ϊ��");
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

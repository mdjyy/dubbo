package com.mdj.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.mdj.TestService;
import com.mdj.bean.User;
import com.mdj.service.interfaces.IUserService;

@Controller
public class UserAction {

	private static Logger log = Logger.getLogger(UserAction.class);

	@Reference(interfaceName="com.mdj.TestService",check=false)
	private TestService service;
	@Reference(interfaceName="com.mdj.service.interfaces.IUserService",check=false)
	private IUserService userService;
	@RequestMapping("/user.do")
	public void getData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			User user = new User();
			user.setLoginName(name);
			user.setPassword(password);
			user.setEmployDate(new Date());
            Map map = userService.addUser(user);
			user = userService.getUser("admin", "admin");
			map.put("user", user);
			String retJson = JSONObject.toJSONString(map);
			log.info("返回的结果retJson:"+retJson);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(retJson);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}

}

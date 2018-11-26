package com.mdj;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mdj.TestService;


public class Test {
    public static void main(String[] args) {
		/*ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:spring-mvc.xml","classpath:applicationContext.xml"});
		System.out.println(ctx.getBean("testService",TestService.class).getData());
   */   
    	System.out.println(null==null?false:null==null?true:null==null);
    }
}

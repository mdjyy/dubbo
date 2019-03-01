package com.mdj.cache;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheElement;
import org.springframework.data.redis.cache.RedisCacheKey;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.mdj.bean.User;

public class CacheSpringClient {
	@Resource(name="rediscacheManager")
    private RedisCacheManager cacheManage;
	
	public static void main(String[] args) throws Exception{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		RedisCacheManager cacheManage = context.getBean("redisCacheManager",RedisCacheManager.class); 
		System.out.println(cacheManage.getCache("1"));
		//cacheManage.setDefaultExpiration(3000);
		//Cache cache =  cacheManage.getCache("test33");
		//cache.put("", "");
		/*System.out.println(cacheManage.getCacheNames());
		System.out.println(1);
		System.out.println(cacheManage.getCache("redis").get("jj", String.class));
		cacheManage.getCache("redis").put("mdj", "xww");
		System.out.println(cacheManage.getCacheNames());*/
		//System.out.println(new String(CacheClient.getConnection().get(elements.getKeyBytes()),"iso-8859-1"));
		/*User u = cache.get("admin", User.class);
		cache.put("linux", 1222);
		System.out.println(u);
		System.out.println(cache.get("jj").get());*/
		//cache.put("YZJ", "QLSN");
	}
}

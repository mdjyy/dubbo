package com.mdj.cache;

import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import com.mdj.util.SerializeUtil;

public class CacheCluster {
	private transient static Logger log = Logger.getLogger("com.mdj.cache.CacheCluster");
	private static JedisPoolConfig config;
	private static String ip;
	private static int port;
	private static int maxId;
	private static JedisCluster jedis;
	static{
		try {
			Properties p = new Properties();
			p.load(CacheClient.class.getResourceAsStream("/cache.properties"));
			ip = p.getProperty("cache.ip");
			port = Integer.valueOf(p.getProperty("cache.port"));
			maxId = Integer.valueOf(p.getProperty("cache.maxId"));
			config = new JedisPoolConfig(); 
			config.setMaxIdle(maxId); 
			config.setTestOnBorrow(false); 
			HostAndPort hostAndPort = new HostAndPort(ip, port);
			Set<HostAndPort> hostAndPortSet = new HashSet<>();
			hostAndPortSet.add(hostAndPort);
			jedis = new JedisCluster(hostAndPortSet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 从缓存中读取对象
	 * @throws IOException 
	 * */
	public static <T> T getObject(String key,Class<T> clazz) {
		try {
			return (T)SerializeUtil.unserialize(jedis.get(key.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				close(jedis);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 关闭链接
	 * @throws IOException 
	 * */
	public static void close(JedisCluster jedis2) throws IOException{
		jedis2.close();
	}
}

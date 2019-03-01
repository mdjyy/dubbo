package com.mdj.cache;

import java.util.Properties;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.mdj.util.SerializeUtil;

public class CacheClient {
	private static Logger log = Logger.getLogger("com.mdj.cache.CacheClient");
	private static JedisPoolConfig config;
	private static JedisPool jedisPool;
	private static String ip;
	private static int port;
	private static int maxId;
	
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
			jedisPool = new JedisPool(config,ip,port);
		} catch (Exception e) {
			log.info("��ʼ������ʧ��");
			e.printStackTrace();
		}
	}
	/**
	 * @param key keyֵ
	 * @param value valueֵ
	 * ��ȡ�����ַ���ֵ
	 * */
	public static String getValue(String key){
		Jedis jedis = null;
		String value = null;
		try {
			jedis = getConnection();
			value = jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(jedis);
		}
		return value;
	}
	/**
	 * �򻺴��д洢����
	 * */
	public static void putObject(String key,Object object){
		Jedis jedis = null;
		try {
			jedis = getConnection();
			jedis.set(key.getBytes(), SerializeUtil.serialize(object));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(jedis);
		}
	}
	/**
	 * �ӻ����ж�ȡ����
	 * */
	public static <T> T getObject(String key,Class<T> clazz){
		Jedis jedis = null;
		try {
			jedis = getConnection();
			return (T)SerializeUtil.unserialize(jedis.get(key.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(jedis);
		}
		return null;
	}
	/**
	 * ��ȡ����
	 * */
	public static Jedis getConnection(){
		return jedisPool.getResource();
	}
	/**
	 * �ر�����
	 * */
	public static void close(Jedis jedis){
		jedis.close();
	}
}

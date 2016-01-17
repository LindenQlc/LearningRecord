package com.qlc.test.redis;

import java.util.ResourceBundle;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolTest {
	public static JedisPool pool = null;
	static{
		/**
		 * 以下三种方法均可获得redis配置
		 */
//		ResourceBundle bundle = ResourceBundle.getBundle("conf/redis",Locale.getDefault(),JedisPoolTest.class.getClassLoader());
//		ResourceBundle bundle = ResourceBundle.getBundle("conf/redis");
		ResourceBundle bundle = ResourceBundle.getBundle("conf.redis");
		if(bundle == null){
			throw new IllegalArgumentException("[redis.properties] is not found");
		}
		
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(Integer.valueOf(bundle.getString("redis.pool.maxTotal")));
		config.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")));
		config.setMaxWaitMillis(Long.valueOf(bundle.getString("redis.pool.maxWaitMillis")));
		config.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow")));
		config.setTestOnReturn(Boolean.valueOf(bundle.getString("redis.pool.testOnReturn")));
		
		pool = new JedisPool(config, bundle.getString("redis1.ip"), Integer.valueOf(bundle.getString("redis1.port")));
	}
}

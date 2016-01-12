package com.qlc.test.redis;

import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

public class SharedJedisPoolTest {
	public static ShardedJedisPool pool = null;
	static{
		ResourceBundle bundle = ResourceBundle.getBundle("redis");
		if(bundle == null){
			throw new IllegalArgumentException("[redis.properties] is not found");
		}
		
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(Integer.valueOf(bundle.getString("redis.pool.maxActive")));
		config.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")));
		config.setMaxWaitMillis(Long.valueOf(bundle.getString("redis.pool.maxWait")));
		config.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow")));
		config.setTestOnReturn(Boolean.valueOf(bundle.getString("redis.pool.testOnReturn")));
		
		JedisShardInfo jedisShardInfo1 = new JedisShardInfo(bundle.getString("redis1.ip"), Integer.valueOf(bundle.getString("redis1.port")));
		//JedisShardInfo jedisShardInfo2 = new JedisShardInfo(bundle.getString("redis2.ip"), Integer.valueOf(bundle.getString("redis2.port")));
		List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();
		//list.add(jedisShardInfo2);
		list.add(jedisShardInfo1);
		pool = new ShardedJedisPool(config, list);
	}
	
}

package com.qlc.test.redis;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;


public class JedisTest {
	private ApplicationContext context;
	private ShardedJedisPool shardedJedisPool;
	/**
	 * connect redis by jedis
	 */
	@Test
	public void testConnection(){
		Jedis jedis = new Jedis("localhost",6379);
		System.out.println(jedis.ping());
		jedis.close();
	}
	
	/**
	 * basic operate api of jedis
	 */
	@Test
	public void testBaseOperate(){
		Jedis jedis = new Jedis("localhost",6379);
		jedis.set("qlc", "qinlinchong");
		Set<String> list = jedis.keys("*");
		for (String key : list) {
			System.out.println(key);
		}
		jedis.close();
	}
	/**
	 * redisPool
	 */
	@Test
	public void testRedisPool(){
		Jedis jedis = JedisPoolTest.pool.getResource();
		System.out.println(jedis.ping());
		JedisPoolTest.pool.returnResource(jedis);
	}
	/**
	 * JedisSharedInfo
	 */
	@Test
	public void testJedisSharedInfo(){
		ShardedJedis jedis = SharedJedisPoolTest.pool.getResource();
		jedis.set("test", "SharedJedisPoolTest!");
		SharedJedisPoolTest.pool.returnResource(jedis);
	}
	/**
	 * integrate with spring
	 */
	@Before
	public void before(){
		context = new ClassPathXmlApplicationContext("classpath:conf/applicationContext.xml");
	}
	@Test
	public void testSpringJedis(){
		ShardedJedisPool pool = (ShardedJedisPool) context.getBean("shardedJedisPool");
		ShardedJedis jedis = pool.getResource();
		System.out.println(jedis.exists("key1"));
		pool.returnResource(jedis);
	}
}

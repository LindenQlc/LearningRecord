package com.qlc.test.redis;

import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;


public class TestJedis {
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
	 * integrate with spring
	 */
	@Test
	public void testSpringJedis(){
		
	}
}

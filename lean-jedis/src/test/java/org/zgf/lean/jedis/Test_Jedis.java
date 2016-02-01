package org.zgf.lean.jedis;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import redis.clients.jedis.Jedis;

/***
 * 
 * jedis 中方法和 命令行中命令基本一致，比较简单
 *
 */
public class Test_Jedis {
	
	protected static Jedis jedis;
	
	@BeforeClass
	public static  void setUp(){
		String serverHost = "172.17.155.51";
		Integer serverPort = 6379;
		jedis = new Jedis(serverHost, serverPort);
	}
	
	@AfterClass
	public static void tearDown(){
		jedis.close();
	}
	
	
	/****      redis String 类型         ****/
	@Test
	public void test_str(){
		jedis.set("name", "zong");
		jedis.setnx("age", "20");
		jedis.setex("birthday", 10, "2015-10-10");
		jedis.psetex("grade", 1000, "10");
		jedis.mset("A","aaaaaa","B","bbbbb","C","cccccc");
		jedis.msetnx("A","aaaaaa","B","bbbbb","C","cccccc");
		//。。。
		
		String name = jedis.get("name");
		System.out.println("name = " + name);
	}
	
	/****      redis List 类型         ****/
	
	@Test
	public void test_lpop(){
		jedis.lpush("namelist", "zhangsan");
		jedis.lpush("namelist", "lisi");
		jedis.lpush("namelist", "wangwu");
		
		List<String> nameList = jedis.lrange("namelist", 0, -1);
		for (String name : nameList) {
			System.out.println(name);
		}
	}

}

package org.zgf.lean.lean_jedis;

import org.junit.Test;

public class Test_1_String extends BaseTest{
	
	@Test
	public void test_set(){
		jedis.set("name", "hello");
		
		System.out.println(jedis.get("name"));
	}
	
	@Test
	public void test(){
		System.out.println("hh");
	}

}

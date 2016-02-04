package org.zgf.learn.mybatis.mapper.one2one;

import org.junit.Test;
import org.zgf.learn.mybatis.entity.one2one.Passenger;
import org.zgf.learn.mybatis.mapper.abase.BasicTest;

public class Test_one2one extends BasicTest{
	
	/**
	 * 执行结果：发送一条连接sql语句
	 */
	@Test
	public void test_get_join(){
		
		Passenger passenger = session.selectOne("org.zgf.learn.mybatis.entity.PassengerMapper.getPassengerById",1);
		System.out.println(passenger);
	}
	
	/**
	 * 执行结果：发送两条sql 语句
	 */
	@Test
	public void test_get(){
		
		Passenger passenger = session.selectOne("org.zgf.learn.mybatis.entity.PassengerMapper.getPsgById",1);
		System.out.println(passenger);
	}

}

package org.zgf.learn.mybatis.mapper.one2many;

import org.junit.Test;
import org.zgf.learn.mybatis.entity.one2many.Depart;
import org.zgf.learn.mybatis.mapper.abase.BasicTest;

public class Test_one2many extends BasicTest{
	
	/**
	 * 表连接查询 
	 */
	@Test
	public void test_1(){
		Depart depart = session.selectOne("org.zgf.learn.mybatis.entity.one2many.DepartMapper.getDepartById",1);
		System.out.println(depart);
	}
	
	/**
	 * 两次查询
	 */
	@Test
	public void test_2(){
		Depart depart = session.selectOne("org.zgf.learn.mybatis.entity.one2many.DepartMapper.getDepartmentById",1);
		System.out.println(depart);
	}

	
	
}

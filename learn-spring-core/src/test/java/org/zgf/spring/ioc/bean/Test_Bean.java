package org.zgf.spring.ioc.bean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zgf.spring.ioc.bean.ComputerBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:org/zgf/spring/ioc/bean/applications-beans.xml")
public class Test_Bean {
	
	@Autowired
	private ComputerBean computerBean;
	
	@Autowired
	private ComputerBean computerStaticFactoryBean;
	
	@Autowired
	private ComputerBean computerInstanceFactoryBean;
	
	@Autowired
	private ComputerBean computerFactoryBean;
	
	/** 1. 全类名配置Bean */
	@Test
	public void test_1(){
		System.out.println("computerBean:" + computerBean);
	}
	
	/** 2. 静态工厂方法配置Bean */
	@Test
	public void test_2(){
		System.out.println("computerStaticFactoryBean:" + computerStaticFactoryBean);
	}
	
	/** 3. 实例工厂方法配置Bean */
	@Test
	public void test_3(){
		System.out.println("computerInstanceFactoryBean:" + computerInstanceFactoryBean);
	}
	
	/** 4. FactoryBean 方式配置Bean */
	@Test
	public void test_4(){
		System.out.println("computerFactoryBean:" + computerFactoryBean);
	}
	

}

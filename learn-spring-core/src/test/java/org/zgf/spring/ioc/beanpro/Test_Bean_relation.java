package org.zgf.spring.ioc.beanpro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:org/zgf/spring/ioc/beanpro/applications-bean-relation.xml")
public class Test_Bean_relation {
	
	@Autowired private MouseBean logicMouseBean;
	@Autowired private MouseBean mouseBean1;
	@Autowired private ComputerBean computerBean;
	
	
	
	@Test
	public void test_1(){
		System.out.println("logicMouseBean:" + logicMouseBean);
	}
	
	@Test
	public void test_2(){
		System.out.println("mouseBean1:" + mouseBean1);
		System.out.println("computerBean:" + computerBean);
	}
	

}

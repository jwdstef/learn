package org.zgf.spring.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zgf.spring.aop.annotation.ICalculator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:org/zgf/spring/annotation/aop/applications-aop.xml")
public class Test_Aop_xml {
	
	@Autowired
	private ICalculator calculator;
	
	@Test
	public void test_add(){
		int result = calculator.add(1, 2);
		System.out.println(result);
	}	
	
	@Test
	public void tesgt_dev(){
		calculator.dev(1, 0);
	}
	
	@Test
	public void test_(){
		calculator.cal(1, 1);
	}
}

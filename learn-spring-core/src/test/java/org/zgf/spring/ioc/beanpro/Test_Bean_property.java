package org.zgf.spring.ioc.beanpro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:org/zgf/spring/ioc/beanpro/applications-bean-property.xml")
public class Test_Bean_property {
	
	@Autowired private ComputerBean computerBean11;
	@Autowired private ComputerBean computerBean12;
	@Autowired private ComputerBean computerBean13;
	@Autowired private ComputerBean computerBean21;
	@Autowired private ComputerBean computerBean22;
	@Autowired private ComputerBean computerBean23;
	@Autowired private ComputerBean computerBean24;
	@Autowired private ComputerBean computerBean25;
	@Autowired private ComputerBean computerBean26;
	@Autowired private ComputerBean computerBean27;
	@Autowired private ComputerBean computerBean28;
	@Autowired private ComputerBean computerBean29;
	@Autowired private ComputerBean computerBean3;
	@Autowired private ComputerBean computerBean4;
	
	/** 1.1测试构造器注入   */
	@Test
	public void test_11(){
		System.out.println("computerBean:" + computerBean11);
	}
	
	/**1.2 测试构造器注入   */
	@Test
	public void test_12(){
		System.out.println("computerBean2:" + computerBean12);
	}
	
	/**1.3 测试构造器注入   */
	@Test
	public void test_13(){
		System.out.println("computerBean3:" + computerBean13);
	}
	
	/** 2.1测试属性注入   */
	@Test
	public void test_21(){
		System.out.println("computerBean4:" + computerBean21);
	}
	
	/** 2.2测试属性注入   */
	@Test
	public void test_22(){
		System.out.println("computerBean22:" + computerBean22.getArrayKeys());
	}
	
	/** 2.3测试属性注入-list   */
	@Test
	public void test_23(){
		System.out.println("computerBean23:" + computerBean23.getListKeys());
	}
	
	/** 2.4测试属性注入-Set   */
	@Test
	public void test_24(){
		System.out.println("computerBean24:" + computerBean24.getSetKeys());
	}
	
	/** 2.5测试属性注入-Map   */
	@Test
	public void test_25(){
		System.out.println("computerBean25:" + computerBean25.getMapKey());
	}
	
	/** 2.6测试属性注入-Properties   */
	@Test
	public void test_26(){
		System.out.println("computerBean26:" + computerBean26.getProperties());
	}
	
	/** 2.7测试属性注入-ref   */
	@Test
	public void test_27(){
		System.out.println("computerBean27:" + computerBean27.getMonitor());
	}

	/** 2.8测试属性注入-内部bean   */
	@Test
	public void test_28(){
		System.out.println("computerBean28:" + computerBean28.getMonitor());
	}
	/** 2.9测试属性注入-自动装配   */
	@Test
	public void test_29(){
		System.out.println("computerBean29:" + computerBean29.getMonitor());
	}
	
	/** 3.测试属性注入-- 外部属性文件  */
	@Test
	public void test_3(){
		System.out.println("computerBean3:" + computerBean3);
	}
	
	/** 4.测试属性注入--P命名空间*/
	@Test
	public void test_4(){
		System.out.println("computerBean4:" + computerBean4);
	}
}

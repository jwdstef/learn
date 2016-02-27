package org.zgf.spring.ioc.beanpro;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test_Bean_LifeCycle {
	
	//配置文件位置
	private String configLocation = "classpath:org//zgf//spring//ioc//beanpro//applications-bean-lifecycle.xml";
	//使用ClassPathXmlApplicationContext 创建spring 上下文
	private ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(configLocation);


	/** 测试作用域    */
	@Test
	public void test_21(){
		MouseBean mouseBean11 = (MouseBean) ctx.getBean("mouseBean1");
		MouseBean mouseBean12 = (MouseBean) ctx.getBean("mouseBean1");
		System.out.println(mouseBean11 == mouseBean12);
	}
	
	@Test
	public void test_22(){
		MouseBean mouseBean11 = (MouseBean) ctx.getBean("mouseBean2");
		MouseBean mouseBean12 = (MouseBean) ctx.getBean("mouseBean2");
		System.out.println(mouseBean11 == mouseBean12);
	}

	/** 测试 生命周期*/
	@Test
	public void test_1(){
		MouseBean mouseBean = (MouseBean) ctx.getBean("mouseBean");
		System.out.println("使用bean：" + mouseBean);
		ctx.close();
	}
	
}

package org.zgf.spring.ioc.beanpro;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 自定义Bean 的后置处理器
 */
public class MyBeanProcessor implements BeanPostProcessor {

	/**
	 * 执行时间：属性的set 方法之后， bean 自定义的init-method 之前
	 * */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("MyPorcessor后置处理器：初始化bean【" + beanName + "】 之前");
		return bean;
	}

	/**
	 * 执行时机：bean 自定义的init-method方法之后
	 * */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("MyPorcessor后置处理器：初始化bean【" + beanName + "】 之后");
		return bean;
	}
}

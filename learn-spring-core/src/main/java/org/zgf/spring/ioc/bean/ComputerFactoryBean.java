package org.zgf.spring.ioc.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * 必须实现FactoryBean接口，及其方法
 *
 */
public class ComputerFactoryBean implements FactoryBean<ComputerBean>{
	
	private String brand;
	
	private String type;
	
	public ComputerFactoryBean( String brand, String type) {
		super();
		this.brand = brand;
		this.type = type;
	}
	
	/** 返回bean 实例 */
	@Override
	public ComputerBean getObject() throws Exception {
		return new ComputerBean(brand, type);
	}

	/** Bean 的类型  */
	@Override
	public Class<?> getObjectType() {
		return ComputerBean.class;
	}

	/** 是否是单例的   */
	@Override
	public boolean isSingleton() {
		return true;
	}
}

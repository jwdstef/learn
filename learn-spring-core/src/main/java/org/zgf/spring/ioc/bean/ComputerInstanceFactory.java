package org.zgf.spring.ioc.bean;

/** 静态工厂类   */
public class ComputerInstanceFactory {
	
	/** 通过品牌 和 型号 创建 电脑对象 ， 非静态方法*/
	public  ComputerBean createComputer(String brand, String type){
		return new ComputerBean(brand, type);
	}
}

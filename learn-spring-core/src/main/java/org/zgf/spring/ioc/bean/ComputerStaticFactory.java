package org.zgf.spring.ioc.bean;

/** 静态工厂类   */
public class ComputerStaticFactory {
	
	/** 通过品牌 和 型号 创建 电脑对象 */
	public static ComputerBean createComputer(String brand, String type){
		return new ComputerBean(brand, type);
	}
}

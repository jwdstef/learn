package org.zgf.learn.hibernate.validator;

import org.junit.Test;

/**
 * 自定义注解：
 * 	自定义注解需要两个步骤：
 * 1. 定义注解接口
 * 2. 定义接口实现类：需要实现ConstraintValidator
 *  
 * @author zonggf
 * 2016年1月24日-下午2:57:55
 */
public class Test_Customerize extends BaseTest {

	@Test
	public void test_biggerThan(){
		this.propertyName = "biggerThan_10";
		this.beanVO.setBiggerThan_10(null);
		this.validateProperty();
		
		this.beanVO.setBiggerThan_10(20);
		this.validateProperty();
		
		this.beanVO.setBiggerThan_10(9);
		this.validateProperty();
		
	}
}

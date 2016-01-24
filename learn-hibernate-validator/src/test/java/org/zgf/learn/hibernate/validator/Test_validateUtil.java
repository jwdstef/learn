package org.zgf.learn.hibernate.validator;

import org.junit.Test;
import org.zgf.learn.hibernate.validator.groups.GroupAdd;
import org.zgf.learn.hibernate.validator.uti.BeanValidateUtil;

/**
 * 测试工具类
 * @author zonggf
 * @date 2016年1月24日-下午4:31:29
 */
public class Test_validateUtil extends BaseTest{
	
	/**
	 * 分组验证：Group.class 必须为接口
	 */
	@Test
	public void test_validateGroups(){
		this.beanVO.setIsNull("sdds");
		this.printValidateResult(BeanValidateUtil.validate(this.beanVO, "isNull",GroupAdd.class));
		
		System.out.println("---------------------------");
		this.printValidateResult(BeanValidateUtil.validate(this.beanVO, "isNull"));
	}
	
	/**
	 * 验证指定属性
	 */
	@Test
	public void test_validateProperty(){
		
		this.beanVO.setNotBlank(null);
		this.printValidateResult(BeanValidateUtil.validate(this.beanVO, "notBlank"));
		
	}
	
	/**
	 * 验证多个属性
	 */
	@Test
	public void test_validateProperties(){
		this.beanVO.setNotBlank(null);
		this.beanVO.setMax_10(100);
		this.printValidateResult(BeanValidateUtil.validate(this.beanVO, new String[]{"notBlank","max_10"}));
	}
	
	/**
	 * 验证对象
	 */
	@Test
	public void test_validateObject(){
		this.beanVO.setNotBlank(null);
		this.beanVO.setMax_10(100);
		this.beanVO.setMin_2(1l);
		this.printValidateResult(BeanValidateUtil.validate(this.beanVO));
	}

}

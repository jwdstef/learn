package org.zgf.learn.hibernate.validator;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

/**
 * JSR 常用注解
 * 
 * @NotNull：
 * @DecimalMax：
 * @DecimalMin：
 * @Digits：
 * @Max：
 * @Min：
 * @Future：
 * @Past：
 * @Pattern：
 * @Size：
 * 
 *  
 * @author zonggf
 * 2016年1月24日-下午2:57:20
 */
public class Test_JSR_303 extends BaseTest{
	
	/**
	 * @NotNull: 试用于所有类型的对象，不能为空
	 */
	@Test 
	public void test_notNull(){
		this.propertyName = "notNull";
		
		this.beanVO.setNotNull(null); 
		this.validateProperty();
	};
	
	
	@Test public void test_DecimalMax(){
		this.propertyName = "decimalMax_80";
		
		this.beanVO.setDecimalMax_80(null);
		this.validateProperty();
		
		this.beanVO.setDecimalMax_80(80.0);
		this.validateProperty();  
		
		this.beanVO.setDecimalMax_80(100.0);
		this.validateProperty();
		
	};
	
	/**
	 * @DecimalMin: 浮点型数据验证大小
	 * 正确情况： 1.null 2. 小于等于规定的大小
	 */
	@Test 
	public void test_DecimalMin(){
		this.propertyName = "decimalMin_20";
		
		this.beanVO.setDecimalMin_20(null);
		this.validateProperty();
		
		this.beanVO.setDecimalMin_20(20.0);
		this.validateProperty();  
		
		this.beanVO.setDecimalMin_20(10.0);
		this.validateProperty();
	};
	
	/**
	 * @Digits: 浮点型格式验证：限制整数部分和小数部分的位数
	 * 正确情况：1. null	2. 整数部分和小数部分位数都符合限制
	 * 
	 */
	@Test public void test_Digits(){
		this.propertyName = "digits_3_2";
		
		this.beanVO.setDigits_3_2(null);
		this.validateProperty();
		
		this.beanVO.setDigits_3_2(201.02);
		this.validateProperty();
		
		this.beanVO.setDigits_3_2(201.011);
		this.validateProperty();
		
	};
	
	/**
	 * @Max: 整型限制最大值，只能用于整数类型：int Integer Long long ...
	 * 正确情况：1. null 2. 小于指定的值
	 */
	@Test 
	public void test_Max(){
		this.propertyName = "max_10";
		
		this.beanVO.setMax_10(null);
		this.validateProperty();
		
		this.beanVO.setMax_10(10);
		this.validateProperty();
		
		this.beanVO.setMax_10(20);
		this.validateProperty();
	};
	
	/**
	 * @Min: 整型限制最大值，只能用于整数类型：int Integer
	 * 正确情况：1. null 2. 小于指定的值
	 */
	@Test public void test_Min(){
		this.propertyName = "max_10";
		
		this.beanVO.setMin_2(null);
		this.validateProperty();
		
		this.beanVO.setMin_2(2l);
		this.validateProperty();
		
		this.beanVO.setMin_2(0l);
		this.validateProperty();
	};
	
	/**
	 * @Size: 修饰String 类型， 限制字符串长度
	 * 正常情况：1.null 2.符合规定的字符串长度
	 */
	@Test 
	public void test_Size(){
		this.propertyName = "size_3_5";
		
		this.beanVO.setSize_3_5(null);
		this.validateProperty();
		
		this.beanVO.setSize_3_5("zong");
		this.validateProperty();
		
		this.beanVO.setSize_3_5("gaofeng");
		this.validateProperty();
	};
	
	@Test public void test_Past(){
		this.propertyName = "past";
		
		this.beanVO.setPast(getPastDate());;
		this.validateProperty();
		
		this.beanVO.setPast(new Date());
		this.validateProperty();
		
	};
	@Test 
	public void test_Future(){
		this.propertyName = "future";
		
		this.beanVO.setFuture(null);
		this.validateProperty();
		
		this.beanVO.setFuture(new Date());
		this.validateProperty();
		
		this.beanVO.setFuture(getPastDate());;
		this.validateProperty();
		
		
	};
	@Test 
	public void test_Pattern(){
		this.propertyName = "pattern_phoneno";
		
		this.beanVO.setPattern_phoneno(null);
		this.validateProperty();
		
		this.beanVO.setPattern_phoneno("18702293691");
		this.validateProperty();
		
		this.beanVO.setPattern_phoneno("464");
		this.validateProperty();
		
	};
	
	
	
	private Date getPastDate(){
		Calendar cal = Calendar.getInstance();
		cal.set(2010, 10, 10);
		return cal.getTime();
	}
	
	
	
	
	
	

}

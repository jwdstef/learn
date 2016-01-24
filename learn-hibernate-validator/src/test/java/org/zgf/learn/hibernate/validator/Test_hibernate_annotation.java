package org.zgf.learn.hibernate.validator;

import org.junit.Test;
/**
 * Hibernator validator 比JSR303 多了如下几个注解
 * 
 * @NotEmpty: 判断字符串是否为空，为空情况：1. null	2. 单个空格
 * @NotBlank： 判断字符串是否为空，为空情况：1. null	2. 单个空格	3. 多个空格
 * @Range： 判断字符串类型的浮点型数字
 * @URL： 判断字符串是否是URL 格式： 正确情况：以http:// 或https://开头
 * @Email： 判断字符串是否是邮箱格式： 正确情况：包含*@*的字符串
 * 
 *  
 * @author zonggf
 * 2016年1月24日-下午2:52:20
 */
public class Test_hibernate_annotation extends BaseTest{
	
	/**
	 * @NotBlank 验证非空, hibernate validator 注解
	 * 正确情况： 1. null	2.  单个空格	3. 多个空格 
	 */
	@Test
	public void test_notBlank(){
		this.propertyName = "notBlank";
		beanVO.setNotBlank("");
		this.validateProperty();
	
		beanVO.setNotBlank(null);
		this.validateProperty();
		
		beanVO.setNotBlank("    ");
		this.validateProperty();
		
		beanVO.setNotBlank("hello");
		this.validateProperty();
	}
	
	/**
	 * @NotEmpty 验证非空, hibernate validator 注解
	 * 正确情况： 1. null	2.  单个空格
	 */
	@Test
	public void test_notEmpty(){
		this.propertyName = "notEmpty";
		beanVO.setNotEmpty("");
		this.validateProperty();
	
		beanVO.setNotEmpty(null);
		this.validateProperty();
		
		beanVO.setNotEmpty("    ");
		this.validateProperty();
		
		beanVO.setNotEmpty("hello");
		this.validateProperty();
	}
	
	
	/**
	 * @Email 验证邮箱 hibernate validator 注解
	 * 正确情况: 1. null	2. 正确邮箱 	3. 含有@注解，并且@符合左右都有字符
	 * 注：只能做简单的邮箱验证而已
	 */
	@Test
	public void test_email(){
		this.propertyName = "email";
		
		beanVO.setEmail(null);
		this.validateProperty();
		
		beanVO.setEmail("5400@qq.com");
		this.validateProperty();
		
		beanVO.setEmail("s@2");
		this.validateProperty();
		
		beanVO.setEmail("s@");
		this.validateProperty();
		
		beanVO.setEmail("hello.world");
		this.validateProperty();
		
	}
	
	/**
	 * @URL 验证链接地址   hibernate validator 注解
	 * 正确情况： 1. null 	2. 以http://开头	3. 以https://开头
	 */
	@Test
	public void test_url(){
		this.propertyName = "url";
		
		beanVO.setUrl(null);
		this.validateProperty();
		
		beanVO.setUrl("http://www.baidu.com");
		this.validateProperty();
		
		beanVO.setUrl("http://");
		this.validateProperty();
		
		beanVO.setUrl("https://");
		this.validateProperty();
		
		beanVO.setUrl("://");
		this.validateProperty();
		
		beanVO.setUrl("s://");
		this.validateProperty();
		
	}
	
	/**
	 * @Length 注解：hibernate validator 注解 
	 * 正常情况： 1. null 	2. 满足约束条件【min, max】 
	 * 注意： 只能修饰字符串 
	 */
	@Test
	public  void test_Length(){
		this.propertyName = "length_4_20";
		
		beanVO.setLength_4_20(null);
		this.validateProperty();
		
		beanVO.setLength_4_20("zong");
		this.validateProperty();
		
		beanVO.setLength_4_20("zongzongzongzongzong");
		this.validateProperty();
		
		beanVO.setLength_4_20("sd");
		this.validateProperty();
	}
	
	/**
	 * @Range 验证字符串形式的小数：
	 * 正确情况： 1. null 	2. 符合自定义小数整数位数的 数字，必须是能转换为数字的字符串
	 */
	@Test
	public void test_range(){
		this.propertyName = "range_1_3";
		
		beanVO.setRange(null);
		this.validateProperty();
		
		beanVO.setRange("1.2");
		this.validateProperty();
		
		beanVO.setRange("1.242");
		this.validateProperty();
		
		beanVO.setRange("1.1a");
		this.validateProperty();
		
		beanVO.setRange("");
		this.validateProperty();
		
		beanVO.setRange("21.242");
		this.validateProperty();
		
		beanVO.setRange("1234");
		this.validateProperty();
		
	}
	
	

}

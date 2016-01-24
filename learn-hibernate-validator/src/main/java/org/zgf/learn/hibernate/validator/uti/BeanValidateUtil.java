package org.zgf.learn.hibernate.validator.uti;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
/**
 * 对象格式验证
 * @author zonggf
 * 2016年1月24日-上午10:15:16
 */
public class BeanValidateUtil {
	
	static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	static Validator validator = factory.getValidator();
	
	/**
	 * 验证对象
	 * @param object
	 * @return Set<ConstraintViolation<T>>
	 */
	public static <T> Set<ConstraintViolation<T>> validate(T object){
		return validator.validate(object);
	}
	
	/**
	 * 分组验证对象
	 * @param object 要验证的对象
	 * @param groups 组列表
	 * @return Set<ConstraintViolation<T>>
	 */
	public static<T>Set<ConstraintViolation<T>> validate(T object, Class<?>... groups){
		return validator.validate(object, groups);
	}
	
	/**
	 * 验证属性
	 * @param object 验证对象
	 * @param propertyName 属性名称
	 * @return Set<ConstraintViolation<T>>
	 */
	public static <T> Set<ConstraintViolation<T>> validate(T object, String propertyName){
		return validator.validateProperty(object, propertyName);
	}
	
	/**
	 * 分组验证对象指定属性
	 * @param object 要验证的对象
	 * @param propertyName 属性名称
	 * @param groups 分组列表
	 * @return Set<ConstraintViolation<T>>
	 */
	public static <T> Set<ConstraintViolation<T>> validate(T object, String propertyName, Class<?>... groups){
		return validator.validateProperty(object, propertyName, groups);
	}
	
	/**
	 * 验证对象指定属性列表
	 * @param object 要验证的对象
	 * @param propertyNames 属性数组
	 * @return Set<ConstraintViolation<T>>
	 */
	public static <T> Set<ConstraintViolation<T>> validate(T object, String... propertyNames){
		Set<ConstraintViolation<T>> resultSet = new LinkedHashSet<>();
		
		Set<ConstraintViolation<T>> propertySet;
		for (String propertyName : propertyNames) {
			propertySet = validator.validateProperty(object, propertyName);
			resultSet.addAll(propertySet);
		}
		return resultSet;
	}
	
	/**
	 * 分组验证指定的属性集合
	 * @param object 待验证的对象
	 * @param propertyNames 属性数组
	 * @param groups 分组列表
	 * @return Set<ConstraintViolation<T>>
	 */
	public static <T> Set<ConstraintViolation<T>> validate(T object, String[] propertyNames, Class<?>... groups){
		Set<ConstraintViolation<T>> resultSet = new LinkedHashSet<>();
		
		Set<ConstraintViolation<T>> propertySet;
		for (String propertyName : propertyNames) {
			propertySet = validator.validateProperty(object, propertyName);
			resultSet.addAll(propertySet);
		}
		return resultSet;
	}
}

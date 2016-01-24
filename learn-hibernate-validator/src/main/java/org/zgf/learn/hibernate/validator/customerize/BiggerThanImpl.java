package org.zgf.learn.hibernate.validator.customerize;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.zgf.learn.hibernate.validator.customerize.infc.BiggerThan;
/**
 * 自定义注解实现类
 * 
 * @author zonggf
 * @date 2016年1月24日-下午5:24:06
 */
public class BiggerThanImpl implements ConstraintValidator<BiggerThan, Integer> {
	
	//用于保存注解中传入的value 值
	Integer valueProperty;

	@Override
	public void initialize(BiggerThan biggerThan) {
		valueProperty = biggerThan.value();
	}

	@Override
	public boolean isValid(Integer realValue, ConstraintValidatorContext context) {
		
		if (valueProperty != null && realValue != null && realValue < valueProperty) {
//			自定义message 信息
//			String message = "";
//			context.disableDefaultConstraintViolation();
//			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
			return false;
		} else {
			return true;
		}
	}

}

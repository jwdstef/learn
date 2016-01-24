package org.zgf.learn.hibernate.validator.customerize.infc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.zgf.learn.hibernate.validator.customerize.BiggerThanImpl;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })	//指定注解使用范围
@Retention(RetentionPolicy.RUNTIME)				
@Constraint(validatedBy = BiggerThanImpl.class)//指定注解实现类
public @interface BiggerThan {
	
	int value() default 0;	//自定义注解属性
	String message() default "int value invalid";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}

package org.zgf.learn.hibernate.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidateUtil {
	
	static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	static Validator validator = factory.getValidator();
	
	public static <T> Set<ConstraintViolation<T>> validate(T object){
		return validator.validate(object);
		
	}

}

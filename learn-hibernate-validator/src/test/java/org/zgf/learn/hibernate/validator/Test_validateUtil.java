package org.zgf.learn.hibernate.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;

public class Test_validateUtil {
	
	
	@Test
	public void test_hh(){
		
		Student student = new Student(2, null);
		
		Set<ConstraintViolation<Student>> constraintViolations = ValidateUtil.validate(student);
		System.out.println(constraintViolations);
	}
	

}

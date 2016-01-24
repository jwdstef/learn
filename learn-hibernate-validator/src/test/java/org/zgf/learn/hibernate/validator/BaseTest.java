package org.zgf.learn.hibernate.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;

public class BaseTest {
	protected BeanVO beanVO = new BeanVO();
	protected String propertyName = null; 
	
	protected void validateProperty(){
		Set<ConstraintViolation<BeanVO>> resultSet = BeanValidateUtil.validateProperty(beanVO, this.propertyName);
		if(resultSet.size()>0){
			System.out.println("\n---------------------  error  -------------------");
			printValidateResult(BeanValidateUtil.validateProperty(beanVO, this.propertyName));
		}else{
			System.out.println("\n---------------------- right  ------------------");
		}
	}
	
	/**  打印验证输出结果     */
	protected void printValidateResult(Set<ConstraintViolation<BeanVO>> constraintViolations){
		for (ConstraintViolation<BeanVO> constraintViolation : constraintViolations) {
			System.out.println(constraintViolation);
		}
	}

}

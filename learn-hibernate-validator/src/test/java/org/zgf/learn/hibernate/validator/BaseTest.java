package org.zgf.learn.hibernate.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.zgf.learn.hibernate.validator.bean.BeanVO;
import org.zgf.learn.hibernate.validator.uti.BeanValidateUtil;
/**
 * 测试基础类
 * @author zonggf
 * @date 2016年1月24日-下午5:27:38
 */
public class BaseTest {
	protected BeanVO beanVO = new BeanVO();
	protected String propertyName = null; 
	
	protected void validateProperty(){
		Set<ConstraintViolation<BeanVO>> resultSet = BeanValidateUtil.validate(beanVO, this.propertyName);
		if(resultSet.size()>0){
			System.out.println("\n---------------------  error  -------------------");
			printValidateResult(BeanValidateUtil.validate(beanVO, this.propertyName));
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

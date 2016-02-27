package org.zgf.spring.aop.annotation;

import org.springframework.stereotype.Component;

@Component
public class Calculatror implements ICalculator{
	
	@Override
	public int add(int a, int b){
		int result = a + b;
		return result;
	}
	
	@Override
	public int sub(int a, int b){
		int result = a - b;
		return result;
	}
	
	@Override
	public int mul(int a , int b){
		int result = a * b;
		return result;
	}
	
	@Override
	public int dev(int a, int b){
		int result = a/b;
		return result;
	}

	@Override
	public void cal(int a, int b) {
		
	}
}

package org.zgf.spring.annotation.aop;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
/**
 * 日志 切面：
 * 1. 用@Aspect 注解， 声明为一个切面
 * 2. 用Componet 注解修饰， 加入spring 容器
 * 3. 用Order 来声明切面的优先级，数值越小，优先级越高(可不设置)
 */
@Aspect
@Component
@Order(1)
public class LoggingAspect {
	
	//切入点：声明切入点表达式
	@Pointcut("execution(public * org.zgf.spring.annotation.aop.Calculatror.*(..))")
	public void loggingExpress(){};
	
	//前置通知:在方法执行之前执行
	@Before("loggingExpress()")
	public void beforeMethod(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("Before: The method [ " + methodName + " ] begins with args " + args );
	}
	
	//后置通知：在方法执行之后执行，无论方法是否能正常执行完
	@After("loggingExpress()")
	public void afterMethod(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("After: The method [ " + methodName + " ] finished with args " + args );
	}
	
	//后置返回通知：在方法执行之后执行，无论方法的返回值是Object 或者 null, 但是方法必须正常执行
	@AfterReturning(pointcut="loggingExpress()",returning="result")
	public void afterReturingMethod(JoinPoint joinPoint, Object result){
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("AfterReturing: The method [ " + methodName + " ] finished with args " + args + " and result [ " + result + " ]");
	}
	
	//后置抛出异常通知：在方法执行之后执行，必须抛出了异常才会执行
	@AfterThrowing(pointcut="loggingExpress()",throwing="ex")
	public void afterThrowingMethod(JoinPoint joinPoint, Exception ex){
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		System.out.println("AfterThrowing: The method [ " + methodName + " ] finished with args " + args + " and ex [ " + ex.getMessage() + " ]");
		ex.printStackTrace();
	}
	
	//环绕通知：包含了前置，后置，异常通知，类似于动态代理全过程
	@Around("loggingExpress()")
	public Object aroundMethod(ProceedingJoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
		
		Object result = "null";
		try {
			//前置通知
			System.out.println("Around-Before: The method [ " + methodName + " ] begins with args " + args );
			result = joinPoint.proceed();
			//后置返回通知
			System.out.println("Around-AfterReturing: The method [ " + methodName + " ] begins with args " + args );
		} catch (Throwable e) {
			e.printStackTrace();
			//后置抛出异常通知
			System.out.println("Around-AfterThrowing: The method [ " + methodName + " ] begins with args " + args );
		}finally {
			//后置通知
			System.out.println("Around-After: The method [ " + methodName + " ] begins with args " + args );
		}
		return result;
	}
}

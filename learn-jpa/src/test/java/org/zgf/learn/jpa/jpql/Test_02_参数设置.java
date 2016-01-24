package org.zgf.learn.jpa.jpql;

import javax.persistence.Query;

import org.junit.Test;
import org.zgf.learn.jpa.base.BasicJPATest;
import org.zgf.learn.jpa.entity.jpql.CustomerJPQL;

public class Test_02_参数设置 extends BasicJPATest {
	
	/**TODO 1.jqpl 参数设置
	 * jpql 设置参数有两种形式： 
	 * 1. 位置参数：
	 * 	  1. 隐式位置参数：直接用？ 代替参数 ，参数位置默认从1开始
	 *    2. 显示位置参数：用？+ 数字 代替参数
	 * 2. 命名参数：用：+ 变量名称 代替参数
	 * 3. 混合擦数：混合使用位置参数和命名参数
	 * 注意：在使用 query.getSingleResult(); 方法时，如果没有返回值的话，会抛出异常
	 */
	//参数设置： 隐式位置参数
	@Test
	public void test_jpql_param_1(){
		int id = 1;
		int age = 10;
		String jpql = "SELECT customer FROM CustomerJPQL customer WHERE customer.id = ? AND customer.age > ?";
		Query query = this.entityManager.createQuery(jpql);
		query.setParameter(1, id).setParameter(2, age);
		
		CustomerJPQL customer = (CustomerJPQL) query.getSingleResult();
		System.out.println(customer);
	}
	
	//参数设置：显示位置参数
	@Test
	public void test_jpql_param_2(){
		int id = 1;
		int age = 10;
		String jpql = "SELECT customer FROM CustomerJPQL customer WHERE customer.id = ?3 AND customer.age > ?2";
		Query query = this.entityManager.createQuery(jpql);
		query.setParameter(3, id).setParameter(2, age);
		
		CustomerJPQL customer = (CustomerJPQL) query.getSingleResult();
		System.out.println(customer);
	}
	
	//参数设置：命名参数
	@Test
	public void test_jpql_param_3(){
		int id = 1;
		int age = 10;
		String jpql = "SELECT customer FROM CustomerJPQL customer WHERE customer.id = :id AND customer.age > :age";
		Query query = this.entityManager.createQuery(jpql);
		query.setParameter("id", id).setParameter("age", age);
		
		CustomerJPQL customer = (CustomerJPQL) query.getSingleResult();
		System.out.println(customer);
	}
	
	//参数设置：混合参数
	@Test
	public void test_jpql_param_4(){
		int id = 1;
		int age = 10;
		String jpql = "SELECT customer FROM CustomerJPQL customer WHERE customer.id = ? AND customer.age > :age";
		Query query = this.entityManager.createQuery(jpql);
		query.setParameter(1, id).setParameter("age", age);
		
		CustomerJPQL customer = (CustomerJPQL) query.getSingleResult();
		System.out.println(customer);
	}

}

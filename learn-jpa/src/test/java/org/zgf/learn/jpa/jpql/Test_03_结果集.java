package org.zgf.learn.jpa.jpql;

import java.util.List;

import javax.persistence.Query;

import org.junit.Test;
import org.zgf.learn.jpa.base.BasicJPATest;
import org.zgf.learn.jpa.entity.jpql.CustomerJPQL;

public class Test_03_结果集 extends BasicJPATest {

	/**TODO 2. jqpl 查询结果集类型：
	 * 1. query.getResultList();
	 *    -> List<Entity>:
	 *    -> List<Object>:
	 *    -> List<Object[]>:
	 * 2. query.getSingleResult():
	 * 	  -> Entity:
	 *    -> Object:
	 *    -> Object[]:
	 * 
	 * 1. 返回的是List 还是 Object 取决于使用的query.getResultList() 还是 query.getSingleResult();
	 *    在使用getSingleResult()方法时，如果查询结果不存在，那么会跑出异常
	 * 2. 返回结果是
	 * 
	 */
	
	// List<Entity> 查询全部属性：返回结果自动封装对象 
	@Test
	public void test_result_1(){
		String jpql = "SELECT customer FROM CustomerJPQL customer WHERE customer.id <5";
		Query query = this.entityManager.createQuery(jpql);
		List<CustomerJPQL> customers = query.getResultList();
		for (CustomerJPQL customer : customers) {
			System.out.println(customer);
		}
	}
	
	// List<Entity> 查询部分属性： 使用构造函数封装对象
	@Test
	public void test_result_2(){
		String jpql = "SELECT new CustomerJPQL(customer.id, customer.name) FROM CustomerJPQL customer WHERE customer.id <5";
		Query query = this.entityManager.createQuery(jpql);
		List<CustomerJPQL> customers = query.getResultList();
		for (CustomerJPQL customer : customers) {
			System.out.println(customer);
		}
	}
	
	// List<Object[]>  查询部分属性，结果不会自动封装，返回结果为 List<Object[]> 对象
	@Test
	public void test_result_3(){
		String jpql = "SELECT customer.id, customer.name FROM CustomerJPQL customer WHERE customer.id <5";
		Query query = this.entityManager.createQuery(jpql);
		List<Object[]> objectArray = query.getResultList();
		for (Object[] objects : objectArray) {
			String line = objects[0] + "  " + objects[1];
			System.out.println(line);
		}
	}
	
	// 查询： 查询部分属性，返回结果为Object[] 对象
	@Test
	public void test_result_4(){
		String jpql = "SELECT customer.id, customer.name FROM CustomerJPQL customer WHERE customer.id =1";
		Query query = this.entityManager.createQuery(jpql);
		Object[] objects = (Object[]) query.getSingleResult();
		String line = objects[0] + "  " + objects[1];
		System.out.println(line);
	}
	
	// 查询：部分属性，返回结果为 Object
	@Test
	public void test_result_5(){
		String jpql = "SELECT customer.name FROM CustomerJPQL customer WHERE customer.id =1";
		Query query = this.entityManager.createQuery(jpql);
		Object object = (Object) query.getSingleResult();
		System.out.println(object);
	}
	
	//查询： 部分属性，返回结果为 List<Object> 
	@Test
	public void test_result_6(){
		String jpql = "SELECT customer.name FROM CustomerJPQL customer WHERE customer.id <5";
		Query query = this.entityManager.createQuery(jpql);
		List<Object> objectList = (List<Object>) query.getResultList();
		for (Object object : objectList) {
			System.out.println(object);
		}
	}
}

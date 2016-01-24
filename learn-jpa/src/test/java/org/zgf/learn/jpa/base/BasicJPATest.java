package org.zgf.learn.jpa.base;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * 这是一个JPA 的测试类，  该类的功能如下：
 * 1. 在每个测试方法之前调用setup 方法，开启事务并获取entityManager 对象。
 * 2. 在每个测试方法之后调用teardown 方法， 提交事务，并关闭entityManager 对象
 * @author Silence
 */
public class BasicJPATest {
	public EntityManagerFactory entityManagerFactory;
	public EntityManager entityManager;
	public EntityTransaction entityTransaction;
		
	//子类的每个测试方法之前都会调用
	@Before
	public void setup(){
		this.entityManagerFactory = Persistence.createEntityManagerFactory("learn-jpa");
		this.entityManager = this.entityManagerFactory.createEntityManager();
		this.entityTransaction = this.entityManager.getTransaction();
		this.entityTransaction.begin();
	}
	
	//子类的每个测试方法之后都会调用
	@After
	public void tearDown(){
		try{
			this.entityTransaction.commit();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.entityManager.close();
			this.entityManagerFactory.close();
		}
	}
	
	@Test
	public void testCreateTables(){
		System.out.println("数据库表创建成功。。。");
	}
	
}

package org.zgf.learn.jpa.cache;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;
import org.zgf.learn.jpa.entity.cache2.HumanEntity;

/**
 * 测试二级缓存：
 * 
 * JPA 使用二级缓存的步骤：
 * 1. 在persistence.xml 配置文件中，开启二级缓存
 * 2. 在persistence.xml 配置文件中，指定二级缓存模式
 * 3. 在persistence.xml 配置文件中，指定实现产品，并引入相关jar包
 * 4. 在要缓存的实体类上添加 @Cacheable(true) 属性
 *
 */
public class Test_HumanEntity{
	public EntityManagerFactory entityManagerFactory;
	public EntityManager entityManager;
	public EntityTransaction entityTransaction;
	
	@Before
	public void setUp(){
		this.entityManagerFactory = Persistence.createEntityManagerFactory("learn-jpa");
	}
	
	/**
	 * 一个entityManager 只有一个 entityTransaction
	 */
	@Test
	public void tst(){
		this.entityManager = this.entityManagerFactory.createEntityManager();
		this.entityTransaction = this.entityManager.getTransaction();
		System.out.println(this.entityTransaction);
		this.entityTransaction.begin();
		this.entityTransaction.commit();
		System.out.println(this.entityTransaction);
		
//		org.hibernate.jpa.internal.TransactionImpl@1b8e3a71
//		org.hibernate.jpa.internal.TransactionImpl@1b8e3a71
	}
	
	/** 添加一条记录  */
	@Test
	public void test_persist(){
		this.entityManager = this.entityManagerFactory.createEntityManager();
		this.entityTransaction = this.entityManager.getTransaction();
		this.entityTransaction.begin();
		
		HumanEntity studentCacheEntity = new HumanEntity("zong");
		this.entityManager.persist(studentCacheEntity);
		
		this.entityManager.close();
	}
	
	/** 测试二级缓存查询：
	 *  1. 如果实体类上注解为 @Cacheable(true) ， 则会发送一条sql 语句
	 *  2. 如果实体类上注解为 @Cacheable(false) , 则会发送两条sql 语句
	 */
	@Test
	public void test_cache2(){
		Integer id = 1;
		
		//第一次查询
		this.entityManager = this.entityManagerFactory.createEntityManager();
		this.entityManager.find(HumanEntity.class, id);
		this.entityManager.close();
		
		//第二次查询
		this.entityManager = this.entityManagerFactory.createEntityManager();
		this.entityManager.find(HumanEntity.class, id);
		this.entityManager.close();
		
	}
	
	
}

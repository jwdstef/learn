package org.zgf.learn.jpa.jpql;

import org.hibernate.jpa.QueryHints;
import org.junit.Test;
import org.zgf.learn.jpa.base.BasicJPATest;
import org.zgf.learn.jpa.entity.jpql.CustomerJPQL;

public class Test_10_二级缓存 extends BasicJPATest {
	
	//使用二级缓存, 只发送一条sql
		@Test
		public void test_hibernate_Cache(){
			String jpql = "SELECT customer FROM CustomerJPQL customer WHERE customer.id = 1 ";
			CustomerJPQL customer = (CustomerJPQL) this.entityManager.createQuery(jpql).setHint(QueryHints.HINT_CACHEABLE, true).getSingleResult();
			System.out.println("------------------------------------------------------------------------------------");
			
			CustomerJPQL customer2 = (CustomerJPQL) this.entityManager.createQuery(jpql).setHint(QueryHints.HINT_CACHEABLE, true).getSingleResult();
			System.out.println(customer);
			System.out.println(customer2);
		}
		
		
		//不使用二级缓存， 发送两条sql
		@Test
		public void test_hibernate_Cache_no(){
			String jpql = "SELECT customer FROM CustomerJPQL customer WHERE customer.id = 1 ";
			CustomerJPQL customer = (CustomerJPQL) this.entityManager.createQuery(jpql).getSingleResult();
			System.out.println("------------------------------------------------------------------------------------");
			
			CustomerJPQL customer2 = (CustomerJPQL) this.entityManager.createQuery(jpql).getSingleResult();
			System.out.println(customer);
			System.out.println(customer2);
		}

}

package org.zgf.learn.jpa.jpql;

import org.junit.Test;
import org.zgf.learn.jpa.base.BasicJPATest;
import org.zgf.learn.jpa.entity.jpql.CustomerJPQL;

public class Test_06_子查询 extends BasicJPATest {

	//测试子查询 
		@Test
		public void test_subQuery(){
			String jpql = "SELECT customer FROM CustomerJPQL customer WHERE customer.id = (SELECT MAX(id) FROM CustomerJPQL)";
			CustomerJPQL customer = (CustomerJPQL) this.entityManager.createQuery(jpql).getSingleResult();
			System.out.println(customer);
		}
		
}

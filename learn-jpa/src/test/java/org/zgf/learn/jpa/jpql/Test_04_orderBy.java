package org.zgf.learn.jpa.jpql;

import java.util.List;

import javax.persistence.Query;

import org.junit.Test;
import org.zgf.learn.jpa.base.BasicJPATest;
import org.zgf.learn.jpa.entity.jpql.CustomerJPQL;

public class Test_04_orderBy extends BasicJPATest {
	
	//测试order by查询
		@Test
		public void test_orderBy(){
			String jpql = "SELECT customer FROM CustomerJPQL customer WHERE customer.id <5 ORDER BY customer.id DESC";
			Query query = this.entityManager.createQuery(jpql);
			List<CustomerJPQL> customers = query.getResultList();
			for (CustomerJPQL customer : customers) {
				System.out.println(customer);
			}
			
		}

}

package org.zgf.learn.jpa.jpql;

import java.util.List;

import javax.persistence.Query;

import org.junit.Test;
import org.zgf.learn.jpa.base.BasicJPATest;

public class Test_05_groupBy extends BasicJPATest {
	
	//测试分组查询
		@Test
		public void test_groupBy(){
			String jpql = "SELECT count(*) FROM CustomerJPQL customer GROUP BY customer.age HAVING customer.age >20";
			Query query = this.entityManager.createQuery(jpql);
			List<Long> cnts = query.getResultList();
			for (Long cnt : cnts) {
				System.out.println(cnt);
			}
		}

}

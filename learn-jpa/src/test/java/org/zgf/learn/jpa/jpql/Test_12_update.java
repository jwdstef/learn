package org.zgf.learn.jpa.jpql;

import org.junit.Test;
import org.zgf.learn.jpa.base.BasicJPATest;

public class Test_12_update extends BasicJPATest {
	//测试update 
		@Test
		public void test_update(){
			String jpql = "UPDATE CustomerJPQL customer SET customer.name = :name WHERE customer.age = :age";
			int cnt = this.entityManager.createQuery(jpql).setParameter("name", "zong_20").setParameter("age", 20).executeUpdate();
			System.out.println("更新了 " + cnt + "条记录");
		}
		

}

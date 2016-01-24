package org.zgf.learn.jpa.jpql;

import org.junit.Test;
import org.zgf.learn.jpa.base.BasicJPATest;

public class Test_11_delete extends BasicJPATest {
	
	//测试delete 在使用jpql 删除的时候，不会进行级联删除，即使设置了级联删除也不行
		@Test
		public void test_delete(){
			int id = 11;
			String jpql = "DELETE FROM CustomerJPQL customer where customer.id = ?";
			int cnt = this.entityManager.createQuery(jpql).setParameter(1, id).executeUpdate();
			System.out.println("删除了 " + cnt + "条记录");
		}

}

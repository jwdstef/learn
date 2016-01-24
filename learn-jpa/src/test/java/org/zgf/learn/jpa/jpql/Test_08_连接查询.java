package org.zgf.learn.jpa.jpql;

import java.util.List;

import org.junit.Test;
import org.zgf.learn.jpa.base.BasicJPATest;
import org.zgf.learn.jpa.entity.jpql.CustomerJPQL;

public class Test_08_连接查询 extends BasicJPATest {
	//连接查询：  如果省略 SELECT OBJECT ,那么需要用 fetch 属性
		@Test
		public void test_join_on_fetch(){
			int id = 1;
			String jpql = "FROM CustomerJPQL customer LEFT OUTER JOIN FETCH customer.orders WHERE customer.id = ? ";
			CustomerJPQL customer = (CustomerJPQL) this.entityManager.createQuery(jpql).setParameter(1, id).getSingleResult();
			System.out.println(customer);
			System.out.println(customer.getOrders());
		}
		
		//连接查询： 如果既省略SELECT OBJECT 又省略 fetch ，那么将不自动封装对象，返回的是List<Object[]> 形式
		@Test
		public void test_join_on(){
			int id = 1;
			String jpql = "FROM CustomerJPQL customer LEFT OUTER JOIN customer.orders WHERE customer.id = ? ";
			List<Object[]> objectList =  this.entityManager.createQuery(jpql).setParameter(1, id).getResultList();
			for (Object[] objects : objectList) {
				System.out.println(objects[0] + " -- " + objects[1]);
			}

//			结果为List<Object> 类型， 不会进行自动封装结果集
//			CustomerJPQL [id=1, name=zong_20, age=20] -- OrderJPQL [id=1, number=00]
//			CustomerJPQL [id=1, name=zong_20, age=20] -- OrderJPQL [id=2, number=01]
//			CustomerJPQL [id=1, name=zong_20, age=20] -- OrderJPQL [id=3, number=02]
		}
		
		//连接查询： 如果 不省略SELECT OBJECT 可以省略 fetch 关键字
		@Test
		public void test_join_on_2(){
			int id = 1;
			String jpql = "SELECT customer FROM CustomerJPQL customer LEFT OUTER JOIN customer.orders WHERE customer.id = ? ";
			CustomerJPQL customer = (CustomerJPQL) this.entityManager.createQuery(jpql).setParameter(1, id).getSingleResult();
			System.out.println(customer);
			System.out.println(customer.getOrders());
		}

}

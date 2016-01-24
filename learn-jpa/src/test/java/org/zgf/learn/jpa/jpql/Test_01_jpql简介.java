package org.zgf.learn.jpa.jpql;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.zgf.learn.jpa.base.BasicJPATest;
import org.zgf.learn.jpa.entity.jpql.CustomerJPQL;
import org.zgf.learn.jpa.entity.jpql.OrderJPQL;

public class Test_01_jpql简介 extends BasicJPATest {
	
	@Test
	public void test_initData(){
		
		this.entityManager.createQuery("delete CustomerJPQL").executeUpdate();
		
		for(int i=0; i<10; i++){
			int age = i%3 + 20;
			CustomerJPQL customerJPQL = new CustomerJPQL("zong_" + i, age);
			
			List<OrderJPQL> orderList = new ArrayList<>();
			for(int j=0; j<3;j++){
				OrderJPQL orderJPQL = new OrderJPQL("" + i + j,customerJPQL);
				orderList.add(orderJPQL);
			}
			customerJPQL.setOrders(orderList);

			this.entityManager.persist(customerJPQL);
		}
	}

}

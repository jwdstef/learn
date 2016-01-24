package org.zgf.learn.jpa.orm.many2many.singleway;

import org.junit.Test;
import org.zgf.learn.jpa.base.BasicJPATest;
import org.zgf.learn.jpa.entity.api.PersonEntity;
import org.zgf.learn.jpa.entity.orm.temp.CustomerEntity;
import org.zgf.learn.jpa.entity.orm.temp.OrderEntity;
/**
 * 测试单向多对一
 */
public class Test_orm_many2one extends BasicJPATest{

	/** Test_persist
	 * 
	 * 在一对多单向关联关系时， 
	 * 	1. 如果 cascade 包含CascadeType.PERSIST，可以使用级联保存， 
	 * 	        在使用级联保存的时候需要注意， 一的一方必须为持久化对象（在em 缓存中） 或者 临时对象（无OID),否则将不能保存
	 *  2. 如果不包含 CascadeType.PERSIST, 那么不能使用级联 保存
	 *  	1. 如果一的一方为持久化对象，则只保存多的一方即可
	 *  	2. 如果一的一方为临时对象，  则需要手动保存一 的一方 和多的一方； 建议先保存一的一方，再保存多的一方 ，否则会多产生Update语句
	 */
	@Test
	public void test_persist_cascadeType_has_Persist(){
	
//		Right: 一的一方为临时对象，无Oid
//		CustomerEntity customerEntity = new CustomerEntity("zong");
		
//		Right: 一的一方为持久化对象（在em 缓存中）
//		CustomerEntity customerEntity = this.entityManager.find(CustomerEntity.class, 1);
		
//		Wrong: 一的一方为非持久化对象， 会抛出异常
		CustomerEntity customerEntity = new CustomerEntity(123,"zong");
		
		OrderEntity order = new OrderEntity("zong_o_1", customerEntity);
		
		//保存多的一方时，会级联保存
		this.entityManager.persist(order);
		
		System.out.println(order);
		System.out.println(customerEntity);
		
	}
	@Test
	public void test_persist_cascadeType_no_Persist(){
		CustomerEntity customerEntity = new CustomerEntity("zong");
		OrderEntity order = new OrderEntity("zong_o_1", customerEntity);
		
		this.entityManager.persist(customerEntity);
		this.entityManager.persist(order);
		
		System.out.println(order);
		System.out.println(customerEntity);
		
	}
	
	/*** 单向多对一，查询多的一方时：
	 *  1. 如果设置了延时加载，那么查询的时候不会使用表连接查询，而是单表操作，当需要用到的时候，才发送sql
	 *  2. 如果设置为立即加载，那么会使用做外连接进行查询
	 */
	@Test
	public void test_query_fetch_LAZY(){
		Integer id = 4;
		OrderEntity orderEntity = this.entityManager.find(OrderEntity.class, id);
		System.out.println("order.orderNum:" + orderEntity.getOrderNum());
		System.out.println("----------------------------------------------");
		System.out.println("order.customre.name:" + orderEntity.getCustomer().getName());
		
//		结果分析：延时加载，发送两条sql 语句
//		Hibernate: select orderentit0_.orderId as orderId1_1_0_, orderentit0_.customerId as customer3_1_0_, orderentit0_.orderNum as orderNum2_1_0_ from jpa_order orderentit0_ where orderentit0_.orderId=?order.orderNum:zong_o_1
//		----------------------------------------------
//		Hibernate: select customeren0_.id as id1_0_0_, customeren0_.name as name2_0_0_ from jpa_customer customeren0_ where customeren0_.id=?
//		order.customre.name:zong
	}
	
	@Test
	public void test_query_fetch_Earge(){
		Integer id = 4;
		OrderEntity orderEntity = this.entityManager.find(OrderEntity.class, id);
		System.out.println("order.orderNum:" + orderEntity.getOrderNum());
		System.out.println("----------------------------------------------");
		System.out.println("order.customre.name:" + orderEntity.getCustomer().getName());
		
//		结果分析：执行的是做外连接语句
//		Hibernate: select orderentit0_.orderId as orderId1_1_0_, orderentit0_.customerId as customer3_1_0_, orderentit0_.orderNum as orderNum2_1_0_, customeren1_.id as id1_0_1_, customeren1_.name as name2_0_1_ 
//				   from jpa_order orderentit0_ 
//				   left outer join jpa_customer customeren1_ on orderentit0_.customerId=customeren1_.id where orderentit0_.orderId=?
//		order.orderNum:zong_o_1
//		----------------------------------------------
//		order.customre.name:zong
	}
	
	@Test
	public void test_update(){
		
	}
	
	
	/***
	 * 1. 如果 cascade 包含CascadeType.REFRESH属性，在对多的一方使用refresh方法时，会同时对一的一方也使用refresh 方法
	 *    执行refresh 的顺序为： 先执行 一 的一方，再执行多的一方 
	 * 
	 */
	@Test
	public void test_refresh(){
		Integer id = 1;
		OrderEntity orderEntity = this.entityManager.find(OrderEntity.class, id);
		System.out.println("------------   refresh 之前   ------------------");
		this.entityManager.refresh(orderEntity);
		
//		结果分析：调用refresh 方法，发送了两条sql 语句
//		Hibernate: select orderentit0_.orderId as orderId1_1_0_, orderentit0_.customerId as customer3_1_0_, orderentit0_.orderNum as orderNum2_1_0_, customeren1_.id as id1_0_1_, customeren1_.name as name2_0_1_ from jpa_order orderentit0_ left outer join jpa_customer customeren1_ on orderentit0_.customerId=customeren1_.id where orderentit0_.orderId=?
//		------------   refresh 之前   ------------------
//		Hibernate: select customeren0_.id as id1_0_0_, customeren0_.name as name2_0_0_ from jpa_customer customeren0_ where customeren0_.id=?
//		Hibernate: select orderentit0_.orderId as orderId1_1_1_, orderentit0_.customerId as customer3_1_1_, orderentit0_.orderNum as orderNum2_1_1_, customeren1_.id as id1_0_0_, customeren1_.name as name2_0_0_ from jpa_order orderentit0_ left outer join jpa_customer customeren1_ on orderentit0_.customerId=customeren1_.id where orderentit0_.orderId=?
	}
	
	
	/*** Test_remove
	 *  在删除多的一方时，如果设置了级联删除，那么在删除多的一方时，也会删除一的一方；
	 *  如果未设置级联删除，那么不会删除一的一方
	 */
	@Test
	public void test_remove(){
		Integer id = 2;
		OrderEntity orderEntity = this.entityManager.find(OrderEntity.class, id);
		this.entityManager.remove(orderEntity);
//		如果设置了级联删除
//		Hibernate: delete from jpa_order where orderId=?
//		Hibernate: delete from jpa_customer where id=?
	}
	
}

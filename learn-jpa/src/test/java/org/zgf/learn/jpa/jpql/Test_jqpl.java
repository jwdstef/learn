package org.zgf.learn.jpa.jpql;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.jpa.QueryHints;
import org.junit.Test;
import org.zgf.learn.jpa.base.BasicJPATest;
import org.zgf.learn.jpa.entity.jpql.CustomerJPQL;
import org.zgf.learn.jpa.entity.jpql.OrderJPQL;

/**
 * 测试单元测试
 * 使用jpql 进行操作的时候，设置的级联查询，级联删除等所有API 有的特性，全部不管用
 *
 */
public class Test_jqpl extends BasicJPATest{
	
	/**
	 * 1. jpql 
	 * 2. jpql 参数赋值
	 * 3. 查询对象、属性
	 * 3. jpql order by
	 * 4. jpql group by
	 * 5. jpql 关联查询 left join fetch
	 * 7. 子查询
	 * 8. 内建函数
	 * 9. 命名查询
	 * 10. update
	 * 11. delete 
	 * 12. 查询缓存
	 */
	
	/**TODO 1.jqpl 参数设置
	 * jpql 设置参数有两种形式： 
	 * 1. 位置参数：
	 * 	  1. 隐式位置参数：直接用？ 代替参数 ，参数位置默认从1开始
	 *    2. 显示位置参数：用？+ 数字 代替参数
	 * 2. 命名参数：用：+ 变量名称 代替参数
	 * 3. 混合擦数：混合使用位置参数和命名参数
	 * 注意：在使用 query.getSingleResult(); 方法时，如果没有返回值的话，会抛出异常
	 */
	//参数设置： 隐式位置参数
	@Test
	public void test_jpql_param_1(){
		int id = 1;
		int age = 10;
		String jpql = "SELECT customer FROM CustomerJPQL customer WHERE customer.id = ? AND customer.age > ?";
		Query query = this.entityManager.createQuery(jpql);
		query.setParameter(1, id).setParameter(2, age);
		
		CustomerJPQL customer = (CustomerJPQL) query.getSingleResult();
		System.out.println(customer);
	}
	
	//参数设置：显示位置参数
	@Test
	public void test_jpql_param_2(){
		int id = 1;
		int age = 10;
		String jpql = "SELECT customer FROM CustomerJPQL customer WHERE customer.id = ?3 AND customer.age > ?2";
		Query query = this.entityManager.createQuery(jpql);
		query.setParameter(3, id).setParameter(2, age);
		
		CustomerJPQL customer = (CustomerJPQL) query.getSingleResult();
		System.out.println(customer);
	}
	
	//参数设置：命名参数
	@Test
	public void test_jpql_param_3(){
		int id = 1;
		int age = 10;
		String jpql = "SELECT customer FROM CustomerJPQL customer WHERE customer.id = :id AND customer.age > :age";
		Query query = this.entityManager.createQuery(jpql);
		query.setParameter("id", id).setParameter("age", age);
		
		CustomerJPQL customer = (CustomerJPQL) query.getSingleResult();
		System.out.println(customer);
	}
	
	//参数设置：混合参数
	@Test
	public void test_jpql_param_4(){
		int id = 1;
		int age = 10;
		String jpql = "SELECT customer FROM CustomerJPQL customer WHERE customer.id = ? AND customer.age > :age";
		Query query = this.entityManager.createQuery(jpql);
		query.setParameter(1, id).setParameter("age", age);
		
		CustomerJPQL customer = (CustomerJPQL) query.getSingleResult();
		System.out.println(customer);
	}
	

	
	
	/**TODO 2. jqpl 查询结果集类型：
	 * 1. query.getResultList();
	 *    -> List<Entity>:
	 *    -> List<Object>:
	 *    -> List<Object[]>:
	 * 2. query.getSingleResult():
	 * 	  -> Entity:
	 *    -> Object:
	 *    -> Object[]:
	 * 
	 * 1. 返回的是List 还是 Object 取决于使用的query.getResultList() 还是 query.getSingleResult();
	 *    在使用getSingleResult()方法时，如果查询结果不存在，那么会跑出异常
	 * 2. 返回结果是
	 * 
	 */
	
	// List<Entity> 查询全部属性：返回结果自动封装对象 
	@Test
	public void test_result_1(){
		String jpql = "SELECT customer FROM CustomerJPQL customer WHERE customer.id <5";
		Query query = this.entityManager.createQuery(jpql);
		List<CustomerJPQL> customers = query.getResultList();
		for (CustomerJPQL customer : customers) {
			System.out.println(customer);
		}
	}
	
	// List<Entity> 查询部分属性： 使用构造函数封装对象
	@Test
	public void test_result_2(){
		String jpql = "SELECT new CustomerJPQL(customer.id, customer.name) FROM CustomerJPQL customer WHERE customer.id <5";
		Query query = this.entityManager.createQuery(jpql);
		List<CustomerJPQL> customers = query.getResultList();
		for (CustomerJPQL customer : customers) {
			System.out.println(customer);
		}
	}
	
	// List<Object[]>  查询部分属性，结果不会自动封装，返回结果为 List<Object[]> 对象
	@Test
	public void test_result_3(){
		String jpql = "SELECT customer.id, customer.name FROM CustomerJPQL customer WHERE customer.id <5";
		Query query = this.entityManager.createQuery(jpql);
		List<Object[]> objectArray = query.getResultList();
		for (Object[] objects : objectArray) {
			String line = objects[0] + "  " + objects[1];
			System.out.println(line);
		}
	}
	
	// 查询： 查询部分属性，返回结果为Object[] 对象
	@Test
	public void test_result_4(){
		String jpql = "SELECT customer.id, customer.name FROM CustomerJPQL customer WHERE customer.id =1";
		Query query = this.entityManager.createQuery(jpql);
		Object[] objects = (Object[]) query.getSingleResult();
		String line = objects[0] + "  " + objects[1];
		System.out.println(line);
	}
	
	// 查询：部分属性，返回结果为 Object
	@Test
	public void test_result_5(){
		String jpql = "SELECT customer.name FROM CustomerJPQL customer WHERE customer.id =1";
		Query query = this.entityManager.createQuery(jpql);
		Object object = (Object) query.getSingleResult();
		System.out.println(object);
	}
	
	//查询： 部分属性，返回结果为 List<Object> 
	@Test
	public void test_result_6(){
		String jpql = "SELECT customer.name FROM CustomerJPQL customer WHERE customer.id <5";
		Query query = this.entityManager.createQuery(jpql);
		List<Object> objectList = (List<Object>) query.getResultList();
		for (Object object : objectList) {
			System.out.println(object);
		}
	}
	
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
	
	//测试子查询 
	@Test
	public void test_subQuery(){
		String jpql = "SELECT customer FROM CustomerJPQL customer WHERE customer.id = (SELECT MAX(id) FROM CustomerJPQL)";
		CustomerJPQL customer = (CustomerJPQL) this.entityManager.createQuery(jpql).getSingleResult();
		System.out.println(customer);
	}
	
	//测试内建函数 
	@Test
	public void test_jpql_function(){
		
		
	}
	
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

//		结果为List<Object> 类型， 不会进行自动封装结果集
//		CustomerJPQL [id=1, name=zong_20, age=20] -- OrderJPQL [id=1, number=00]
//		CustomerJPQL [id=1, name=zong_20, age=20] -- OrderJPQL [id=2, number=01]
//		CustomerJPQL [id=1, name=zong_20, age=20] -- OrderJPQL [id=3, number=02]
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
	
	
	//测试命名查询 
	@Test
	public void test_namedQuery(){
		String nativeQueryName = "queryById";
		CustomerJPQL customer = (CustomerJPQL) this.entityManager.createNamedQuery(nativeQueryName).setParameter(1, 1).getSingleResult();
		System.out.println(customer);
	}
	
	@Test
	public void test_namedNativeQuery(){
		String nativeQueryName = "queryCnt";
		BigInteger totalCnt = (BigInteger) this.entityManager.createNamedQuery(nativeQueryName).getSingleResult();
		System.out.println("总共有" + totalCnt.intValue() + "条记录");
	}
	
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
	
	//测试delete 在使用jpql 删除的时候，不会进行级联删除，即使设置了级联删除也不行
	@Test
	public void test_delete(){
		int id = 11;
		String jpql = "DELETE FROM CustomerJPQL customer where customer.id = ?";
		int cnt = this.entityManager.createQuery(jpql).setParameter(1, id).executeUpdate();
		System.out.println("删除了 " + cnt + "条记录");
	}
	
	//测试update 
	@Test
	public void test_update(){
		String jpql = "UPDATE CustomerJPQL customer SET customer.name = :name WHERE customer.age = :age";
		int cnt = this.entityManager.createQuery(jpql).setParameter("name", "zong_20").setParameter("age", 20).executeUpdate();
		System.out.println("更新了 " + cnt + "条记录");
	}
	
	//测试本地sql 查询 :注意返回类型
	@Test
	public void test_sql(){
		String sql = "select count(*) from jpa_jpql_customer c where c.age >:age";
		BigInteger cnt = (BigInteger) this.entityManager.createNativeQuery(sql).setParameter("age", 20).getSingleResult();
		System.out.println("年龄大于20 的一共有" + cnt.intValue() + "位顾客");
	}
	
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

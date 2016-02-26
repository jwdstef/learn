package org.zgf.learn.jpa.orm.one2many;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.zgf.learn.jpa.base.BasicJPATest;
import org.zgf.learn.jpa.entity.orm.one2many.Address;
import org.zgf.learn.jpa.entity.orm.one2many.User;

/**
 * 单向一对多测试
 *
 */
public class Test_User extends BasicJPATest{
	
	
	/**
	 * 单向 1-n 保存时，一定会多发送Update 语句
	 * 原因： 因为单向1-n 关系中，由1 的一方来维护关联关系，所以在插入n的一方的时候，不会维护外键，及不会给外键赋值。
	 *     所以在保存1的一方时，一定会执行多条 sql语句，更新n 的一方
	 */
	@Test
	public void test_persist(){
		
		List<Address> addresses = new ArrayList<>();
		Address address1 = new Address("北京","西城");
		Address address2 = new Address("天津","西青");
		addresses.add(address1);
		addresses.add(address2);
		
		System.out.println("-------  开始保存1 的一方  - -----------"); 
		User user = new User("zong");
		user.setAddresses(addresses);
		this.entityManager.persist(user);
		this.entityManager.persist(address1);
		this.entityManager.persist(address2);
		
//		-------  开始保存1 的一方  - -----------
//		Hibernate: insert into jpa_o2o_user (name) values (?)
//		Hibernate: update jpa_o2o_address set userId=? where id=?
//		Hibernate: update jpa_o2o_address set userId=? where id=?

	}
	
	
	@Test
	public void test_findByjpql(){
		String jqpl = "select user from User user where user.id = 1";
		User user = (User) this.entityManager.createQuery(jqpl).getSingleResult();
		System.out.println("————————————————————————————————————————");
		System.out.println(user.getAddresses().size());
	}
	
	@Test
	public void test_find(){
		Integer userId = 1;
		User user = this.entityManager.find(User.class, userId);
		System.out.println("————————————————————————————————————————");
		System.out.println(user.getAddresses().size());
		
//		默认使用懒加载模式, 可以更改fetch 属性
//		Hibernate: select user0_.id as id1_3_0_, user0_.name as name2_3_0_ from jpa_o2o_user user0_ where user0_.id=?
//		————————————————————————————————————————
//		Hibernate: select addresses0_.userId as userId4_3_0_, addresses0_.id as id1_2_0_, addresses0_.id as id1_2_1_, addresses0_.city as city2_2_1_, addresses0_.province as province3_2_1_ from jpa_o2o_address addresses0_ where addresses0_.userId=?

	}
	
	/**
	 * 单向一对一：
	 * 在删除一的一方时，默认会对n 的一方执行 update 语句，将相应外键设置为null。 注意查看实体类 上，外键设置是否允许为null
	 * 如果想使用级联删除，可以使用 cascade 属性设置
	 */
	@Test
	public void test_remove(){
		Integer userId = 1;
		User user = this.entityManager.find(User.class, userId);
		System.out.println("——————————————  开始删除实体   ——————————————————————————");
		
		this.entityManager.remove(user);
		
//		Hibernate: select user0_.id as id1_3_0_, user0_.name as name2_3_0_ from jpa_o2o_user user0_ where user0_.id=?
//		——————————————  开始删除实体   ——————————————————————————
//		Hibernate: update jpa_o2o_address set userId=null where userId=?
//		Hibernate: delete from jpa_o2o_user where id=?
	}
	

}

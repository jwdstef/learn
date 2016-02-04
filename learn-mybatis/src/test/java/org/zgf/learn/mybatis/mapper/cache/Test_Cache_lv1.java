package org.zgf.learn.mybatis.mapper.cache;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.zgf.learn.mybatis.entity.api.Person;
import org.zgf.learn.mybatis.mapper.abase.BasicTest;

/**
 * 测试mybatis 一级缓存
 * 
 * 1. mybatis 的一级缓存，是基于PerpetualCache 的 HashMap本地缓存， 是session 级别的缓存，缓存作用域为 Session
 * 2. mybatis 的一级缓存，默认开启，只是在同一session 中生效，使用条件比较苛刻：
 * 			1. 必须是同一session   2. 使用一级缓存之前不能有任何主动（调用session.clearCache() 方法或被动出发清空session缓存（执行了某个对象的增，删，改）的方法
 * 3. 以下情况为不能使用一级缓存的方式：
 * 		1. 不在同一session 
 * 		2. 主动调用了session.clearCache()方法
 * 		3. session 开启期间对某个对象执行了增删，改的操作
 */
public class Test_Cache_lv1 extends BasicTest{
	
	/**
	 * 结果分析： 只执行了两个sql 语句
	 * 结论 ：默认使用一级缓存，一级缓存时session 级别的
	 */
	@Test
	public void test_useCache1(){
		
		//1. 第一次执行查询：执行两条sql 语句
		System.out.println("--------------  第一次查询   --------------");
		session.selectOne("org.zgf.learn.mybatis.entity.PersonMapper.getPersonById",1);
		session.selectOne("org.zgf.learn.mybatis.entity.UserMapper.getUserById",1);
		
		//2. 第二次执行查询：不执行sql语句，从一级缓存中获取
		System.out.println("--------------  第二次查询   --------------");
		session.selectOne("org.zgf.learn.mybatis.entity.PersonMapper.getPersonById",1);
		session.selectOne("org.zgf.learn.mybatis.entity.UserMapper.getUserById",1);
	}
	
	/**
	 * 执行结果：执行两条sql 语句
	 * 结论：不同的session， 不同的session 直接不能使用一级缓存功能，因为mybatis的一级缓存时针对 session 而言的 
	 */
	@Test
	public void test_no_diff_session(){
		
		//1. 用新的session 查询：执行一条sql 语句
		SqlSession session2 = sessionFactory.openSession();
		session2.selectOne("org.zgf.learn.mybatis.entity.PersonMapper.getPersonById",1);
		session2.commit();
		
		//2. 用原有session 请求：执行一条sql 语句，因为不在同一个session 
		session2.selectOne("org.zgf.learn.mybatis.entity.PersonMapper.getPersonById",1);		
	}
	
	/**
	 * 执行结果： 执行两条sql 语句
	 * 结论：主动调用session 的clearCache 清空缓存
	 */
	@Test
	public void test_no_clearCache(){
		
		//1. 执行第一次查询：执行一条sql 语句
		session.selectOne("org.zgf.learn.mybatis.entity.PersonMapper.getPersonById",1);
		
		//2. 调用session 清空缓存方法
		session.clearCache();
		
		//3. 执行第二次查询：执行一条sql 语句，因为session 的缓存清空了
		session.selectOne("org.zgf.learn.mybatis.entity.PersonMapper.getPersonById",1);
		
	}
	
	/**
	 * 执行结果：执行四条sql语句
	 * 结论：在打开的session期间，任何对象的增，删，改操作，都会将整个session 里面的缓存清空，而不是说只清空session缓存中该对象的相关信息
	 */
	@Test
	public void test_no_update(){
		
		//1. 查询：执行两条sql语句
		System.out.println("--------------  第一次查询   --------------");
		session.selectOne("org.zgf.learn.mybatis.entity.PersonMapper.getPersonById",1);
		session.selectOne("org.zgf.learn.mybatis.entity.UserMapper.getUserById",1);
		
		//2. 对 person1 执行更新操作：对任意对象执行都会把整个session 清空
		System.out.println("--------------  对 person执行更新操作   --------------");
		Person person = new Person("name2",20,"mm");
		person.setId(1);
		session.update("org.zgf.learn.mybatis.entity.PersonMapper.updatePerson", person);
		
		//3. 重新执行查询：执行两条sql语句
		System.out.println("--------------  重新执行查询  --------------");
		session.selectOne("org.zgf.learn.mybatis.entity.PersonMapper.getPersonById",1);
		session.selectOne("org.zgf.learn.mybatis.entity.UserMapper.getUserById",1);
		
	}
	
	/**
	 * 执行结果：执行四条sql语句
	 * 结论：在打开的session期间，任何对象的增，删，改操作，都会将整个session 里面的缓存清空，而不是说只清空session缓存中该对象的相关信息
	 */
	@Test
	public void test_no_insert(){
		
		//1. 查询：执行两条sql语句
		System.out.println("--------------  第一次查询   --------------");
		session.selectOne("org.zgf.learn.mybatis.entity.PersonMapper.getPersonById",1);
		session.selectOne("org.zgf.learn.mybatis.entity.UserMapper.getUserById",1);
		
		//2. 对 person 执行插入：对任意对象执行都会把整个session 清空
		System.out.println("--------------  对 person执行插入操作   --------------");
		Person person = new Person("name2",20,"mm");
		session.insert("org.zgf.learn.mybatis.entity.PersonMapper.addPerson",person);
		System.out.println(person);
		
		//3. 重新执行查询：执行两条sql语句
		System.out.println("--------------  重新执行查询  --------------");
		session.selectOne("org.zgf.learn.mybatis.entity.PersonMapper.getPersonById",1);
		session.selectOne("org.zgf.learn.mybatis.entity.UserMapper.getUserById",1);
	}
	
	/**
	 * 执行结果：执行四条sql语句
	 * 结论：在打开的session期间，任何对象的增，删，改操作，都会将整个session 里面的缓存清空，而不是说只清空session缓存中该对象的相关信息
	 */
	@Test
	public void test_no_delete(){
		
		//1. 查询：执行两条sql语句
		System.out.println("--------------  第一次查询   --------------");
		session.selectOne("org.zgf.learn.mybatis.entity.PersonMapper.getPersonById",1);
		session.selectOne("org.zgf.learn.mybatis.entity.UserMapper.getUserById",1);
		
		//2. 对 person 执行插入：对任意对象执行都会把整个session 清空
		System.out.println("--------------  对 person执行插入操作   --------------");
		int delNum = session.insert("org.zgf.learn.mybatis.entity.PersonMapper.deletePersonById",7);
		System.out.println("删除了：" + delNum + "条数据");
		
		//3. 重新执行查询：执行两条sql语句
		System.out.println("--------------  重新执行查询  --------------");
		session.selectOne("org.zgf.learn.mybatis.entity.PersonMapper.getPersonById",1);
		session.selectOne("org.zgf.learn.mybatis.entity.UserMapper.getUserById",1);
		
	}
	
	
}

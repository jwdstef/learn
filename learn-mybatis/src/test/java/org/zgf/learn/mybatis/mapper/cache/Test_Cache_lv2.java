package org.zgf.learn.mybatis.mapper.cache;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.zgf.learn.mybatis.mapper.abase.BasicTest;

/**
 * 测试二级缓存
 * 
 * 1. mybatis 的二级缓存也是基于PerpetualCache 的 HashMap本地缓存，作用域为namespace,可指定缓存的数据源（如Ehcache）。
 * 2. mybatis 的二级缓存使用步骤：
 * 		1. 实体类必须实现接口：Serializable
 * 		2. mapper 文件必须启用缓存，即添加 <Cache></Cache>
 */
public class Test_Cache_lv2 extends BasicTest{
	
	/**
	 * 执行结果：执行了两条sql 语句
	 * 结论： 使用了二级缓存
	 */
	@Test
	public void test_cache2(){
		
		//1. 用新的session 开启：执行了一条sql
		SqlSession session2 = sessionFactory.openSession();
		session2.selectOne("org.zgf.learn.mybatis.entity.UserCache2Mapper.getUserById",1);
		session2.close();
		
		//2. 用原来的session 查询：不执行sql
		session.selectOne("org.zgf.learn.mybatis.entity.UserCache2Mapper.getUserById",1);
	}
	
	
	/**
	 * 执行结果： 执行了一条sql语句
	 * 结论： 没有使用二级缓存
	 */
	@Test
	public void test_cache2_no(){
		
		//1. 用新的session 开启：执行了一条sql
		SqlSession session2 = sessionFactory.openSession();
		session2.selectOne("org.zgf.learn.mybatis.entity.UserMapper.getUserById",1);
		session2.close();
		
		//2. 用原来的session 查询：不执行sql
		session.selectOne("org.zgf.learn.mybatis.entity.UserMapper.getUserById",1);
	}

}

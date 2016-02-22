package org.zgf.learn.mybatis.mapper.abase;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
/**
 * mybatis 基础测试类
 * @author zongf
 * @time 2016年2月8日-下午12:08:40
 *
 */
public class BasicTest {
	
	private static final String configSource = "conf.xml";
	
	//session 工厂
	public static SqlSessionFactory sessionFactory;
	
	//session 
	public static SqlSession session;
	
	
	//类创建的时候，初始化session 工厂
	@BeforeClass
	public static void init(){
		try {
			Reader reader = Resources.getResourceAsReader(configSource);
			sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 每个单元测试用例执行之前，新打开一个session（需要手动提交事务的session）
	 * @author zonggf
	 * @time 2016年2月8日-下午12:11:09
	 */
	@Before
	public void setUp(){
		if(sessionFactory != null){
			session = sessionFactory.openSession();
		}
	}
	
	/**
	 * 每个测试用例执行之后，提交事务，然后关闭session
	 * @author zonggf
	 * @time 2016年2月8日-下午12:10:42
	 */
	@After
	public void tearDown(){
		if(session != null){
			session.commit();
			session.close();
		}
	}
}

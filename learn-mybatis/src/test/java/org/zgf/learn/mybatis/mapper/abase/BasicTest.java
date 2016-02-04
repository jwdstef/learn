package org.zgf.learn.mybatis.mapper.abase;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BasicTest {
	
	
	private static final String configSource = "conf.xml";
	
	public static SqlSessionFactory sessionFactory;
	
	public static SqlSession session;
	
	@BeforeClass
	public static void init(){
		try {
			Reader reader = Resources.getResourceAsReader(configSource);
			sessionFactory = new SqlSessionFactoryBuilder().build(reader);
			session = sessionFactory.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Before
	public void setUp(){
		if(sessionFactory != null){
			session = sessionFactory.openSession();
		}
	}
	
	@After
	public void tearDown(){
		if(session != null){
			session.commit();
			session.close();
		}
	}
}

package org.zgf.learn.jpa.jpql;

import java.math.BigInteger;

import org.junit.Test;
import org.zgf.learn.jpa.base.BasicJPATest;
import org.zgf.learn.jpa.entity.jpql.CustomerJPQL;

public class Test_09_命名查询 extends BasicJPATest {

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

}

package org.zgf.learn.jpa.jpql;

import java.math.BigInteger;

import org.junit.Test;
import org.zgf.learn.jpa.base.BasicJPATest;

public class Test_13_sql extends BasicJPATest {

	//测试本地sql 查询 :注意返回类型
	@Test
	public void test_sql(){
		String sql = "select count(*) from jpa_jpql_customer c where c.age >:age";
		BigInteger cnt = (BigInteger) this.entityManager.createNativeQuery(sql).setParameter("age", 20).getSingleResult();
		System.out.println("年龄大于20 的一共有" + cnt.intValue() + "位顾客");
	}

}

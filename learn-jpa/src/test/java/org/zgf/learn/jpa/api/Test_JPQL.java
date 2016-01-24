package org.zgf.learn.jpa.api;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.junit.Test;
import org.zgf.learn.jpa.base.BasicJPATest;
import org.zgf.learn.jpa.entity.api.PersonEntity;
/** 
 * 
 * 测试jpql 语句
 *
 */
public class Test_JPQL extends BasicJPATest{

	/**
	 * Test_API_createQuery: 测试jpql 查询
	 */
	@Test
	public void test_createQuery_String(){
		String jpql = "from PersonEntity personEntity";
		Query query = this.entityManager.createQuery(jpql);
		List<PersonEntity> personList = query.getResultList();
		for (PersonEntity personEntity : personList) {
			System.out.println(personEntity);
		}
	}
	
	/**Test_API_createNamedQuery: 测试命名查询
	 * 注意：命名查询的sql 为jpql 语法
	 * 
	 */
	@Test
	public void test_namedQuery_jpql(){
		String namedQueryName = "queryByName";
		Query query = this.entityManager.createNamedQuery(namedQueryName);
		//设置命名参数
		query.setParameter("name", "zhangsan");
		PersonEntity person = (PersonEntity) query.getSingleResult();
		System.out.println(person);
	}
	
	/**Test_API_createNativeQuery: 测试本地查询
	 * 
	 */
	@Test
	public void test_sqlQuery(){
		List<PersonEntity> personList = new ArrayList<>();
		String sql = "select p.id, p.s_name, p.age from tb_person p";
		Query query = this.entityManager.createNativeQuery(sql);
		List list = query.getResultList();
		for(int i=0;i<list.size(); i++){
			PersonEntity person = new PersonEntity();
			Object[] objectArray = (Object[]) list.get(i);
			person.setId((Integer)objectArray[0]);
			person.setName((String)objectArray[1]);
			person.setAge((Integer)objectArray[2]);
			personList.add(person);
		}
		for(PersonEntity personEntity: personList){
			System.out.println(personEntity);
		}
	}
}

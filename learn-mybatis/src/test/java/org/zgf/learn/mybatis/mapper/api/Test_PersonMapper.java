package org.zgf.learn.mybatis.mapper.api;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.zgf.learn.mybatis.entity.api.Person;
import org.zgf.learn.mybatis.mapper.abase.BasicTest;

public class Test_PersonMapper extends BasicTest{
	
	
	String statement = "";
	String namespace = "org.zgf.learn.mybatis.entity.PersonMapper.";
	
	/** TODO mybatis 基本标签使用    **/
	@Test
	public void test_addPerson(){
		Person person = new Person("zhangsan",20,"man");
		this.statement = this.namespace + "addPerson";
		session.insert(statement, person);
		System.out.println(person);
	}
	
	@Test
	public void test_deletePersonById(){
		this.statement = this.namespace + "deletePersonById";
		int delNum = session.delete(statement, 6);
		
		System.out.println("删除了：" + delNum + " 条数据");
	}
	
	@Test
	public void test_updatePerson(){
		this.statement = this.namespace + "updatePerson";
		
		Person person = new Person("lisi",30,"women");
		person.setId(1);
		
		int delNum = session.update(statement,person);
		System.out.println("更新了：" + delNum + "条数据");
	}
	
	@Test
	public void test_getPersonById(){
		this.statement = this.namespace + "getPersonById";
		Person person = session.selectOne(statement,1);
		System.out.println(person);
	}

	@Test
	public void test_getPersons(){
		this.statement = this.namespace + "getPersons";
		List<Person> personList = session.selectList(statement);
		for (Person person : personList) {
			System.out.println(person);
		}
	}
	
	
	
	/*** TODO 动态sql 语句测试  ***/
	
	/** 测试 foreach 用法 */
	@Test
	public void test_addPersonList(){
		List<Person> personList = new ArrayList<>();
		Person person = new Person("zong_101", 10, "man");
		Person person2 = new Person("zong_102", 20, "boy");
		Person person3 = new Person("zogn_103", 30, "boy");
		
		personList.add(person);
		personList.add(person2);
		personList.add(person3);
		
		this.statement += "addPersonList";
		int insertNum = session.insert(statement, personList);
		System.out.println("共插入数据：" + insertNum + " 条");
	}
	
	@Test
	public void test_getInPersons(){
		List<Integer> ageList = new ArrayList<>();
		ageList.add(10);
		ageList.add(20);
		
		this.statement += "getInPersons";
		List<Person>  personList = session.selectList(this.statement, ageList);
		for (Person person : personList) {
			System.out.println(person);
		}
	}
	
	/** 测试 if 用法    */
	@Test
	public void test_getPersonsByAge(){
//		Integer age = 20;
		Integer age = null;
		
		this.statement += "getPersonsByAge";
		List<Person>  personList = session.selectList(this.statement, age);
		for (Person person : personList) {
			System.out.println(person);
		}
	}
	
	@Test
	public void test_getPersonsByPersonAge(){
//		Integer age = 20;
		Integer age = null;
		
		this.statement += "getPersonsByPersonAge";
		Person personCondition = new Person(null,age,null);
		List<Person>  personList = session.selectList(this.statement, personCondition);
		for (Person person : personList) {
			System.out.println(person);
		}
	}
	
	
	/** 测试：choose (when, otherwise)  */
	@Test
	public void test_getPersonsBySex(){
		String sex = null;
		
		this.statement += "getPersonsBySex";
		List<Person> personList = session.selectList(this.statement,sex);
		for (Person person : personList) {
			System.out.println(person);
		}
	}
	
	
	/** 测试：Where*/
	@Test
	public void test_getPersonByMTAgeEQSex(){
//		Person personCondition = new Person(null,null,null);
//		Person personCondition = new Person(null,20,null);
		Person personCondition = new Person(null,null,"man");
//		Person personCondition = new Person(null,20,"man");
		
		
		this.statement += "getPersonByMTAgeEQSex";
		List<Person> personList = session.selectList(this.statement,personCondition);
		for (Person person : personList) {
			System.out.println(person);
		}
	}
	
	/**  Set **/
	@Test
	public void test_updatePersonPropertyNotNull(){
//		Person person = new Person(1, "zong_update",26,"man");
		Person person = new Person(1, null, null, null);
		this.statement += "updatePersonPropertyNotNull";
		int updateNum = session.update(this.statement,person);
		System.out.println("更新了 " + updateNum + " 条记录");
	}
	
	/**  trim **/
	@Test
	public void test_updatePersonPropertyNotNullByTrim(){
//		Person person = new Person(1, "zong_update",26,"man");
		Person person = new Person(1, null, null, null);
		this.statement += "updatePersonPropertyNotNull";
		int updateNum = session.update(this.statement,person);
		System.out.println("更新了 " + updateNum + " 条记录");
	}
	
	
}

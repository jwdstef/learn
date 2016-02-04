package org.zgf.learn.mybatis.mapper.api;

import java.util.List;

import org.junit.Test;
import org.zgf.learn.mybatis.entity.api.Person;
import org.zgf.learn.mybatis.mapper.abase.BasicTest;

public class Test_PersonMapper extends BasicTest{
	
	
	String statement = "";
	String namespace = "org.zgf.learn.mybatis.entity.PersonMapper.";
	
	
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
	
}

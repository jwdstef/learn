package org.zgf.spring.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zgf.spring.entity.User;
import org.zgf.spring.jdbc.UserJTDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:org/zgf/spring/jdbc/applications-jdbc.xml")
public class Test_UserDao {
	
	@Autowired
	private UserJTDao userJTDao;
	
	@Test
	public void test_addUser(){
		User user = new User("zong","gaofeng",20);
		this.userJTDao.addUser(user);
	}
	
	@Test
	public void test_addBatch(){
		List<User> users = new ArrayList<>();
		users.add(new User("zong_01","zong_01",30));
		users.add(new User("zong_02","zong_03",30));
		users.add(new User("zong_03","zong_03",30));
		
		this.userJTDao.addBatchUser(users);
		System.out.println("成功添加了" + users.size() + " 条记录");
	}
	
	@Test
	public void test_getById(){
		int id = 1;
		User user = this.userJTDao.getUserById(id);
		System.out.println(user);
	}
	
	@Test
	public void test_getUsersByAge(){
		List<User> userList = this.userJTDao.getUsersByAge(30);
		for (User user : userList) {
			System.out.println(user);
		}
	}
	
	@Test
	public void test_getUsernameById(){
		int id = 10;
		String username = this.userJTDao.getUserName(id);
		System.out.println(username);
	}
	
	@Test
	public void test_getUsernames(){
		int age = 30;
		List<String> usernames = this.userJTDao.getUserNames(age);
		System.err.println("usernames:" + usernames);
	}
	
	

}

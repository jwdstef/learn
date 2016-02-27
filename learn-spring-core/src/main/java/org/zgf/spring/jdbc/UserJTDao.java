package org.zgf.spring.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.zgf.spring.entity.User;
/**
 *  JdbcTemplate
 *  1. JdbcTemplate 是Spring 框架对jdbc 的一层封装
 *  2. JdbcTemplate 查询时 只支持位置参数，不支持命名参数
 *  3. JdbcTemplate 可以对结果集进行封装，但是不支持级联属性
 */
@Repository
public class UserJTDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String tableName = " spring_jdbc_user ";
	
	/** 执行 insert , update , delete 语句   */
	public void addUser(User user){
		String  sql = "insert into " + tableName + " values(null,?,?,?)";
		this.jdbcTemplate.update(sql, user.getUsername(),user.getPassword(),user.getAge());
	}
	
	/*** 批量执行：insert, update, delete 语句   */
	public void addBatchUser(List<User> users){
		String  sql = "insert into  " + tableName + "  values(null,?,?,?)";
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		for(int i=0; i<users.size();  i++){
			Object[] objectArray = new Object[]{users.get(i).getUsername(), users.get(i).getPassword(), users.get(i).getAge()};
			batchArgs.add(objectArray);
		}
		 this.jdbcTemplate.batchUpdate(sql, batchArgs);
	}
	
	/** 查询单个对象,进行结果集封装  */
	public User getUserById(Integer id){
		String sql = "select * from " + tableName + " where id = ? ";
		//结果集映射规则
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
	}
	
	/**  查询列表   */
	public List<User> getUsersByAge(int age){
		String sql = "select * from " + tableName + " where age = ?" ;
		//结果集映射方式
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		return this.jdbcTemplate.query(sql, rowMapper, age);
		
	}
	
	/**  查询某一字段   */
	public String getUserName(int id){
		String sql = "select username from " + tableName + " where id = ? ";
		return this.jdbcTemplate.queryForObject(sql, String.class, id);
	}
	
	/** 查询某一字段数组   */
	public List<String> getUserNames(int age){
		String sql = "select username from " + tableName + " where age = ?" ;
		//结果集映射方式
		return this.jdbcTemplate.queryForList(sql, String.class, age);
	}
}

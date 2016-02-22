package org.zgf.learn.mybatis.mapper.advance;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.zgf.learn.mybatis.entity.advance.Student;
import org.zgf.learn.mybatis.mapper.abase.BasicTest;

public class Test_advance extends BasicTest{
	
	String namespace = "org.zgf.learn.mybatis.entity.advance.StudentMapper.";
	String statement = "";
	
	/** 
	 * TODO 测试调用存储过程：通过存储过程动态创建表
	 */
	@Test
	public void test_produce(){
		statement = namespace + "createStudentTable";
		Object obj = session.update(statement, 101);
		System.out.println(obj);
	}
	
	
	/** 
	 * TODO 测试批量插入
	 */
	@Test
	public void test_batchAdd(){
		statement = namespace + "addStudents";
		List<Student> studentList = this.createStudentList();
		Long start = System.currentTimeMillis();
		session.insert(statement,studentList);
		Long end = System.currentTimeMillis();
		System.out.println("批量插入" + studentList.size() + "  条数据耗时：" + (end - start));
		//批量插入1000 条数据耗时：655
		//批量插入2000  条数据耗时：879
		//批量插入5000  条数据耗时：1993
		//批量插入20000  条数据耗时：3148
		//批量插入30000  条数据耗时：4535
	}
	
	/** 
	 * TODO 测试普通插入
	 */
	@Test
	public void test_normalAdd(){
		statement = namespace + "addStudent";
		List<Student> studentList = this.createStudentList();
		Long start = System.currentTimeMillis();
		for (Student student : studentList) {
			session.insert(statement,student);
		}
		session.insert(statement);
		Long end = System.currentTimeMillis();
		System.out.println("普通插入" + studentList.size() + " 条数据耗时：" + (end - start));
		//普通插入1000 条数据耗时：1906
		//普通插入2000 条数据耗时：2738
		//普通插入5000 条数据耗时：4824
		//普通插入20000 条数据耗时：12908
		//普通插入30000 条数据耗时：17610
	}
	
	
	public List<Student> createStudentList(){
		List<Student> studentList = new ArrayList<>();
		for(int i=0; i<30000; i++){
			Student student = new Student("zong_" + i, 20 + 100%5);
			studentList.add(student);
		}
		return studentList;
	}

}

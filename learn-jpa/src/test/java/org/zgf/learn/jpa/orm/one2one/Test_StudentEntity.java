package org.zgf.learn.jpa.orm.one2one;

import org.junit.Test;
import org.zgf.learn.jpa.base.BasicJPATest;
import org.zgf.learn.jpa.entity.orm.one2one.doubleway.StudentCard;
import org.zgf.learn.jpa.entity.orm.one2one.doubleway.Student;

public class Test_StudentEntity extends BasicJPATest{
	
	
	@Test
	public void test_save(){
		Student studentEntity = new Student("zong");
		StudentCard studentCardEntity = new StudentCard("12313");
		
		this.entityManager.persist(studentEntity);
	}
	
	
	

}

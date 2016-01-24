package org.zgf.learn.jpa.entity.orm.one2one.doubleway;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "jpa_o2o_student")
public class Student {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;
	
	/**
	 * @OneToOne: 表示该关系为一对对关系，因为有mappedBy 属性，因此可以断定是双向关联关系
	 */
	@OneToOne(mappedBy = "student")
	private StudentCard studentCardEntity;

	public Student() {
		super();
	}

	public Student(String name) {
		super();
		this.name = name;
	}

	public Student(Integer id, String name, StudentCard studentCardEntity) {
		super();
		this.id = id;
		this.name = name;
		this.studentCardEntity = studentCardEntity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StudentCard getStudentCardEntity() {
		return studentCardEntity;
	}

	public void setStudentCardEntity(StudentCard studentCardEntity) {
		this.studentCardEntity = studentCardEntity;
	}

	@Override
	public String toString() {
		return "StudentEntity [id=" + id + ", name=" + name + ", studentCardEntity=" + studentCardEntity + "]";
	}

}

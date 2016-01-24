package org.zgf.learn.jpa.entity.orm.many2one;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "jpa_m2o_employee")
public class Employee {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;
	
	@JoinColumn(name="departmentId")
	@ManyToOne
	private Department department;

	public Employee() {
		super();
	}

	public Employee(String name) {
		super();
		this.name = name;
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

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}

}

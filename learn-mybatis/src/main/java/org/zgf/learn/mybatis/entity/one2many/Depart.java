package org.zgf.learn.mybatis.entity.one2many;

import java.util.Set;

public class Depart {

	private Integer id;

	private Integer departno;

	private String departname;

	private Set<Emp> employees;

	public Depart() {
	}

	public Depart(Integer departno, String departname, Set<Emp> employees) {
		this.departno = departno;
		this.departname = departname;
		this.employees = employees;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDepartno() {
		return departno;
	}

	public void setDepartno(Integer departno) {
		this.departno = departno;
	}

	public String getDepartname() {
		return departname;
	}

	public void setDepartname(String departname) {
		this.departname = departname;
	}

	public Set<Emp> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Emp> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Department [departno=" + departno + ", departname=" + departname + ", employees=" + employees + "]";
	}

}

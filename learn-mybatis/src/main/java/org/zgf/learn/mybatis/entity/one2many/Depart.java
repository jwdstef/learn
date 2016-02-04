package org.zgf.learn.mybatis.entity.one2many;

import java.util.List;

public class Depart {

	private Integer id;

	private Integer departno;

	private String departname;

	private List<Emp> employees;

	public Depart() {
	}

	public Depart(Integer departno, String departname, List<Emp> employees) {
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

	public List<Emp> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Emp> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Depart [id=" + id + ", departno=" + departno + ", departname=" + departname + ", employees=" + employees
				+ "]";
	}

}

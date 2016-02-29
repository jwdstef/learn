package org.zgf.learn.freemarker;

public class Employee {

	private String name;

	private int age;

	private String sex;

	public Employee() {
		super();
	}

	public Employee(String name, int age, String sex) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}

}

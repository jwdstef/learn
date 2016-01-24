package org.zgf.learn.jpa.entity.orm.one2many;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "jpa_o2m_user")
public class User {

	@Id
	@GeneratedValue
	private Integer id;
	private String name;

	//@JoinColumn 指定外键的相关信息
	//外键设置为nullable = true, 那么在不设置级联删除的情况下，执行remove 语句，会报错，因为remove 方法会将外键更新为null
	@JoinColumn(name = "userId",unique=false,nullable=false) 
	@OneToMany
	private List<Address> addresses;

	public User() {
		super();
	}

	public User(String name) {
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

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", addresses=" + addresses + "]";
	}

}

package org.zgf.learn.jpa.entity.orm.temp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jpa_temp_customer")
public class CustomerEntity {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	public CustomerEntity() {
		super();
	}

	public CustomerEntity(String name) {
		super();
		this.name = name;
	}
	
	public CustomerEntity(Integer id, String name) {
		super();
		this.id = id;
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
		return "Customer [id=" + id + ", name=" + name + "]";
	}

}

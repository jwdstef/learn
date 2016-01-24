package org.zgf.learn.jpa.entity.orm.one2many2one;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "jpa_o2m2o_customer")
public class Customer {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	@OneToMany(mappedBy="customer")
	private List<ProductOrder> orders;

	public Customer() {
		super();
	}

	public Customer(String name) {
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

	public List<ProductOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<ProductOrder> orders) {
		this.orders = orders;
	}

	public Customer(Integer id, String name, List<ProductOrder> orders) {
		super();
		this.id = id;
		this.name = name;
		this.orders = orders;
	}

}

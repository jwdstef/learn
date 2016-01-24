package org.zgf.learn.jpa.entity.jpql;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "jpa_jpql_order")
public class OrderJPQL {

	@Id
	@GeneratedValue
	private Integer id;

	private String number;

	@JoinColumn(name = "customerId")
	@ManyToOne(fetch = FetchType.LAZY)
	private CustomerJPQL customer;

	public OrderJPQL() {
	}

	public OrderJPQL(String number, CustomerJPQL customer) {
		super();
		this.number = number;
		this.customer = customer;
	}

	public OrderJPQL(String number) {
		this.number = number;
	}

	public OrderJPQL(Integer id, String number) {
		super();
		this.id = id;
		this.number = number;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public CustomerJPQL getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerJPQL customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "OrderJPQL [id=" + id + ", number=" + number + "]";
	}

}

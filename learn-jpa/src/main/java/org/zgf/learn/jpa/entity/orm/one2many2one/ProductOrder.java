package org.zgf.learn.jpa.entity.orm.one2many2one;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="jpa_o2m2o_product_order")
public class ProductOrder {

	@Id
	@GeneratedValue
	private Integer orderId;

	private String orderNum;
	
	@JoinColumn(name="customerId")
	@ManyToOne
	private Customer customer;

	public ProductOrder() {
		super();
	}

	public ProductOrder(String orderNum) {
		super();
		this.orderNum = orderNum;
	}
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "ProductOrder [orderId=" + orderId + ", orderNum=" + orderNum + ", customer=" + customer + "]";
	}

}

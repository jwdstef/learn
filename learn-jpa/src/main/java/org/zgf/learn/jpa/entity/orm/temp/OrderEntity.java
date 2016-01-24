package org.zgf.learn.jpa.entity.orm.temp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "jpa_temp_order")
public class OrderEntity {

	@Id
	@GeneratedValue
	private Integer orderId;

	private String orderNum;

	/**
	 * @ManyToOne 注解标明单向多对一的关系， 
	 * 		fetch 指定是否使用懒加载方式,LAZY 为延时加载, EAGER为立即加载
	 * 		cascade 指定级联方式：
	 * 			CascadeType.PERSIST： 特性：影响persist 方法，采用级联保存，被级联的一方必须为持久化对象或临时对象
	 * 			CascadeType.REFRESH： 特性：影响refresh 方法，采用级联刷新
	 * 			CascadeType.REMOVE：	  特性：影响remove 方法, 采用级联删除
	 * 			CascadeType.MERGE：       特性：影响merge 方法
	 * 			CascadeType.DETACH：    特性：
	 * 			CascadeType.ALL： 具有以上所有的特性
	 * @JoinColumn 指定外键名称
	 * 
	 */
	@JoinColumn(name = "customerId")
	@ManyToOne(fetch = FetchType.EAGER, cascade={CascadeType.REMOVE,CascadeType.REFRESH})
	private CustomerEntity customer;

	public OrderEntity() {
		super();
	}

	public OrderEntity(Integer orderId, String orderNum) {
		super();
		this.orderId = orderId;
		this.orderNum = orderNum;
	}

	public OrderEntity(String orderNum) {
		super();
		this.orderNum = orderNum;
	}

	public OrderEntity(String orderNum, CustomerEntity customer) {
		super();
		this.orderNum = orderNum;
		this.customer = customer;
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

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderNum=" + orderNum + ", customer=" + customer + "]";
	}

}

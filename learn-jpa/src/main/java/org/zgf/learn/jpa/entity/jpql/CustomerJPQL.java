package org.zgf.learn.jpa.entity.jpql;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/***
 * 命名查询时，name 必须唯一，@NamedQuery 和 @NamedNativeQuery 中间不能出现相同的name
 *
 */
@NamedQueries({
	@NamedQuery(name="queryById",query="SELECT customer FROM CustomerJPQL customer WHERE customer.id = ?"),
})
@NamedNativeQueries({
	@NamedNativeQuery(name="queryCnt",query="SELECT count(*) FROM jpa_jpql_customer")
})
@Entity
@Table(name = "jpa_jpql_customer")
public class CustomerJPQL {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	private int age;

	/** 双向一对多时，通常建议在1 的一方使用mappedBy */
	@OneToMany(mappedBy = "customer",fetch=FetchType.LAZY, cascade={CascadeType.PERSIST,CascadeType.REMOVE})
	private List<OrderJPQL> orders;

	public CustomerJPQL() {
		super();
	}

	public CustomerJPQL(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public CustomerJPQL(Integer id, String name) {
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

	public List<OrderJPQL> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderJPQL> orders) {
		this.orders = orders;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "CustomerJPQL [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}

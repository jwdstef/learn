package org.zgf.learn.jpa.entity.orm.one2many;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jpa_o2m_address")
public class Address {

	@Id
	@GeneratedValue
	private Integer id;

	private String province;

	private String city;

	public Address() {
		super();
	}

	public Address(String province, String city) {
		super();
		this.province = province;
		this.city = city;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Address [province=" + province + ", city=" + city + "]";
	}

}

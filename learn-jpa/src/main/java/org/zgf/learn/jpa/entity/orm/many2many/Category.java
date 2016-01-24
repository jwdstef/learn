package org.zgf.learn.jpa.entity.orm.many2many;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "jpa_m2m_category")
public class Category {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	@ManyToMany(mappedBy="")
	private List<Item> items;

	public Category() {
		super();
	}

	public Category(String name) {
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

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", items=" + items + "]";
	}

}

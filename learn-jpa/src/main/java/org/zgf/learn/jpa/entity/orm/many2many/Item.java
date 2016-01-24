package org.zgf.learn.jpa.entity.orm.many2many;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "jpa_m2m_item")
public class Item {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	@JoinTable(name = "jpa_m2m_category_item", joinColumns = {
			@JoinColumn(name = "item_id", referencedColumnName = "id"),
	}, inverseJoinColumns = { @JoinColumn(name = "category_id", referencedColumnName = "id") })
	@ManyToMany
	private List<Category> categories;

	public Item() {
		super();
	}

	public Item(String name) {
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

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", categories=" + categories + "]";
	}

}

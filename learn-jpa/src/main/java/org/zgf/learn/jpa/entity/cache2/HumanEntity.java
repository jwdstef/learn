package org.zgf.learn.jpa.entity.cache2;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 *使用 Cacheable(false)标明使用二级缓存
 *
 */
@Cacheable(false)
@Entity
@Table(name = "jpa_cache2_human")
public class HumanEntity {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	public HumanEntity() {
		super();
	}

	public HumanEntity(String name) {
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
		return "StudentEntity [id=" + id + ", name=" + name + "]";
	}

}

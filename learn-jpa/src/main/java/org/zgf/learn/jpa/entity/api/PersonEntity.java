package org.zgf.learn.jpa.entity.api;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Table(name = "jpa_api_person")
@Entity
@NamedQueries({ // 命名查询注解：只能写JPQL 语句
		@NamedQuery(name = "queryAllByJpql", query = "from PersonEntity personEntity"),
		@NamedQuery(name = "queryByName", query = "from PersonEntity personEntity where personEntity.name = :name") })
public class PersonEntity {

	/**
	 * 测试主键生成策略 GenerationType.AUTO: 根据数据库的默认规则来生成主键,默认选项。
	 * GenerationType.IDENTITY:数据库自增(mysql 适用，Oracle不适用）
	 * GenerationType.SEQUENCE:序列生成方式，（Oracle适用，mysql 不适用）。使用时需要添加注解@SequenceGenerator指定序列名
     * GenerationType.TABLE：       通过表产生主键，框架借由表模拟序列产生主键，使用该策略可以使应用更易于数据库移植，但是使用并不常见。在工作流activiti 貌似有用到。
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	/***
	 * 数据库字段限制： Column可以指定数据库 字段的名称 ，长度，唯一性，是否可以为空,是否可以插入，是否可以更新
	 */
	@Column(name = "s_name", length = 500, unique = false, nullable = false, insertable = true, updatable = true)
	private String name;
	private int age;

	/**
	 * 枚举类型： EnumType.STRING： 指定数据库中存储的是字符串类型
	 * EnumTypee.ORDINAL:指定数据库
	 * 存储的类型为枚举的索引
	 */
	@Enumerated(EnumType.STRING)
	private Gender gender;

	private Double englishScore;
	private Float mathScore;

	/**
	 * 日期类型： TimeStamp 会根据当地的时间做自动转换
	 * TemporalType.DATE: 指定映射数据库中的DATE 类型，只存储日期
	 * TemporalType.TIME: 指定映射数据库 中的TIME类型， 只存储时间
	 * TemporalType.TIMESTAMP:指定映射数据库中的TIMESTAMP类型
	 */
	@Temporal(TemporalType.TIME)
	private Date birthdayTime;
	
	@Temporal(TemporalType.DATE)
	private Date birthdayDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthday;

	/**
	 * 映射大数据字段
	 */
	@Lob 
	@Basic(fetch = FetchType.LAZY, optional = true) // 延迟加载为true,貌似是Hibernate的一个bug，并不能实现延迟加载
	private byte[] info;

	/**
	 * 指定不映射的字段
	 */
	@Transient 
	private String transientProperty;
	
	public PersonEntity() {
		super();
	}

	public PersonEntity(Integer id, String name, int age, Gender gender, Double englishScore, Float mathScore,
			byte[] info, String transientProperty) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.englishScore = englishScore;
		this.mathScore = mathScore;
		this.info = info;
		this.transientProperty = transientProperty;
		this.birthday = new Date();
		this.birthdayDate = new Date();
		this.birthdayTime = new Date();
	}

	public PersonEntity(String name, int age, Gender gender, Double englishScore, Float mathScore, byte[] info,
			String transientProperty) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.englishScore = englishScore;
		this.mathScore = mathScore;
		this.info = info;
		this.transientProperty = transientProperty;
		this.birthday = new Date();
		this.birthdayDate = new Date();
		this.birthdayTime = new Date();
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Double getEnglishScore() {
		return englishScore;
	}

	public void setEnglishScore(Double englishScore) {
		this.englishScore = englishScore;
	}

	public Float getMathScore() {
		return mathScore;
	}

	public void setMathScore(Float mathScore) {
		this.mathScore = mathScore;
	}

	public Date getBirthdayTime() {
		return birthdayTime;
	}

	public void setBirthdayTime(Date birthdayTime) {
		this.birthdayTime = birthdayTime;
	}

	public Date getBirthdayDate() {
		return birthdayDate;
	}

	public void setBirthdayDate(Date birthdayDate) {
		this.birthdayDate = birthdayDate;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public byte[] getInfo() {
		return info;
	}

	public void setInfo(byte[] info) {
		this.info = info;
	}

	public String getTransientProperty() {
		return transientProperty;
	}

	public void setTransientProperty(String transientProperty) {
		this.transientProperty = transientProperty;
	}

	@Override
	public String toString() {
		return "PersonEntity [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", englishScore="
				+ englishScore + ", mathScore=" + mathScore + ", birthdayTime=" + birthdayTime + ", birthdayDate="
				+ birthdayDate + ", birthday=" + birthday + ", transientProperty=" + transientProperty + "]";
	}

}

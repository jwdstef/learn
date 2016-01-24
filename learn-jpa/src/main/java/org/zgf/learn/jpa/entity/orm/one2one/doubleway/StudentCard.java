package org.zgf.learn.jpa.entity.orm.one2one.doubleway;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "jpa_o2o_studentcard")
public class StudentCard {

	@Id
	@GeneratedValue
	private Integer id;

	private String cardNumber;

	/**
	 * @JoinColomn：指名外键信息， 表示当前表中添加外键字段：外键列为 student_id, 唯一
	 * @OneToOne: 指名这是一对一关系， 并且没有mappedby属性，表示该外键由自己维护
	 */
	@JoinColumn(name = "student_id", unique = true)
	@OneToOne
	private Student student;

	public StudentCard() {
		super();
	}

	public StudentCard(Integer id, String cardNumber) {
		super();
		this.id = id;
		this.cardNumber = cardNumber;
	}

	public StudentCard(String cardNumber) {
		super();
		this.cardNumber = cardNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}

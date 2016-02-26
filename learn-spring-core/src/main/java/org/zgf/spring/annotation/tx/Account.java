package org.zgf.spring.annotation.tx;

public class Account {
	
	private Integer id;
	
	private Integer number;
	
	private Integer money;
	
	public Account() {
		super();
	}
	public Account(Integer number, Integer money) {
		super();
		this.number = number;
		this.money = money;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}
}

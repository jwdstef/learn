package org.zgf.learn.mybatis.entity.one2one;

/**
 * 乘客
 */
public class Passenger {

	private Integer id;

	private String name;

	private Integer age;

	private PassengerCard passengerCard;

	public Passenger() {
	}

	public Passenger(Integer id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public PassengerCard getPassengerCard() {
		return passengerCard;
	}

	public void setPassengerCard(PassengerCard passengerCard) {
		this.passengerCard = passengerCard;
	}

	@Override
	public String toString() {
		return "Passenger [id=" + id + ", name=" + name + ", age=" + age + ", passengerCard=" + passengerCard + "]";
	}

}

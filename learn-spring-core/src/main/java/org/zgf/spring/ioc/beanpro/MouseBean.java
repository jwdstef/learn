package org.zgf.spring.ioc.beanpro;

public class MouseBean {
	
	private String brand;
	
	private Double price;
	public MouseBean() {
		System.out.println("Bean--构造方法被调用...");
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
		System.out.println("Bean--setBrand 被调用");
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
		System.out.println("Bean--setPrice 被调用");
	}
	
	@Override
	public String toString() {
		return "MouseBean [brand=" + brand + ", price=" + price + "]";
	}

	private void initeBean(){
		this.brand = "Dell";
		this.price = 2000.0;
		System.out.println("Bean--initeBean 被调用");
	}
	
	private void destoryBean(){
		System.out.println("Bean--destoryBean 被调用");
	}

}

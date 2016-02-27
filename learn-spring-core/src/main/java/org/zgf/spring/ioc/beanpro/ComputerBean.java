package org.zgf.spring.ioc.beanpro;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ComputerBean {

	private String name;

	private double price;

	private String[] arrayKeys;

	private List<String> listKeys;

	private Set<String> setKeys;

	private Map<String, String> mapKey;

	private Properties properties;

	private MonitorBean monitor;

	public ComputerBean() {
	}

	public ComputerBean(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public ComputerBean(ComputerBean computerBean) {
		this.name = computerBean.getName();
		this.price = computerBean.getPrice();
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String[] getArrayKeys() {
		return arrayKeys;
	}

	public void setArrayKeys(String[] arrayKeys) {
		this.arrayKeys = arrayKeys;
	}

	public List<String> getListKeys() {
		return listKeys;
	}

	public void setListKeys(List<String> listKeys) {
		this.listKeys = listKeys;
	}

	public Set<String> getSetKeys() {
		return setKeys;
	}

	public void setSetKeys(Set<String> setKeys) {
		this.setKeys = setKeys;
	}

	public Map<String, String> getMapKey() {
		return mapKey;
	}

	public void setMapKey(Map<String, String> mapKey) {
		this.mapKey = mapKey;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public MonitorBean getMonitor() {
		return monitor;
	}

	public void setMonitor(MonitorBean monitor) {
		this.monitor = monitor;
	}

	@Override
	public String toString() {
		return "ComputerBean [name=" + name + ", price=" + price + "]";
	}

}

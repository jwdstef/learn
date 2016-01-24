package org.zgf.learn.hibernate.validator.bean;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 内部嵌套类
 * 
 * @author zonggf
 * @date 2016年1月24日-下午5:28:17
 */
public class InnerBean {

	@NotBlank(message = "InnerBean.name 不能为空")
	private String name = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

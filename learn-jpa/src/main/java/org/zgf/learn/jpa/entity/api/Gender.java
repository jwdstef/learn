package org.zgf.learn.jpa.entity.api;
/**
 * @Description: 枚举类，映射到数据库中的是BOY 而不是"男"
 * @author zonggf
 * @date 2015年11月4日-下午3:39:36
 */
public enum Gender {

	BOY("男"),GIRL("女");
	
	private String gender;
	
	Gender(String gender){
		this.gender = gender;
	}
	
	public static Gender getGender(String gender){
		Gender[] genderEnumers = Gender.values();
		for (Gender genderEnumer : genderEnumers) {
			if(null != gender){
				if(gender.equals(genderEnumer.toString())){
					return genderEnumer;
				}
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return this.gender;
	}
}

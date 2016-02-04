package org.zgf.learn.jpa.api;

import java.util.Date;

import org.junit.Test;
import org.zgf.learn.jpa.base.BasicJPATest;
import org.zgf.learn.jpa.entity.api.Gender;
import org.zgf.learn.jpa.entity.api.PersonEntity;

public class Testhhh extends BasicJPATest {
	private PersonEntity getPersonEntity(){
		Date date = new Date();
		PersonEntity person = new PersonEntity();
		person.setName("zhangsan");
		person.setAge(20);
		person.setBirthday(date);
		person.setBirthdayDate(date);
		person.setBirthdayTime(date);
		person.setEnglishScore(20.20);
		person.setMathScore(89.8f);
		person.setGender(Gender.BOY);
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<100;i++){
			sb.append("heladn asndsk nasfjgnas nsd gaksn a sdnkg asdn asndk asnd  ansf asd n  asngjka s");
		}
		person.setInfo(sb.toString().getBytes());
		return person;
	}

	@Test
	public void test_1(){
		PersonEntity person = getPersonEntity();
		this.entityManager.persist(person);
		
		PersonEntity p2 = this.entityManager.find(PersonEntity.class, person.getId());
		PersonEntity p3 = this.entityManager.find(PersonEntity.class, person.getId());
		
		System.out.println("------------  result -----------------");
		System.out.println("person:" + person);
		System.out.println("p2:" + p2);
		System.out.println("p3:" + p3);
		
//		Hibernate: insert into jpa_api_person (age, birthday, birthdayDate, birthdayTime, englishScore, gender, info, mathScore, s_name) values (?, ?, ?, ?, ?, ?, ?, ?, ?)
//		------------  result -----------------
//		person:PersonEntity [id=6, name=zhangsan, age=20, gender=男, englishScore=20.2, mathScore=89.8, birthdayTime=Wed Feb 03 16:58:23 CST 2016, birthdayDate=Wed Feb 03 16:58:23 CST 2016, birthday=Wed Feb 03 16:58:23 CST 2016, transientProperty=null]
//		p2:PersonEntity [id=6, name=zhangsan, age=20, gender=男, englishScore=20.2, mathScore=89.8, birthdayTime=Wed Feb 03 16:58:23 CST 2016, birthdayDate=Wed Feb 03 16:58:23 CST 2016, birthday=Wed Feb 03 16:58:23 CST 2016, transientProperty=null]
//		p3:PersonEntity [id=6, name=zhangsan, age=20, gender=男, englishScore=20.2, mathScore=89.8, birthdayTime=Wed Feb 03 16:58:23 CST 2016, birthdayDate=Wed Feb 03 16:58:23 CST 2016, birthday=Wed Feb 03 16:58:23 CST 2016, transientProperty=null]

	}
	
	@Test
	public void test_2(){
		PersonEntity person = getPersonEntity();
		this.entityManager.persist(person);
		
		String jqpl = "select person from PersonEntity person where person.id = " + person.getId();
		PersonEntity p2 = (PersonEntity) this.entityManager.createQuery(jqpl).getSingleResult();
		PersonEntity p3 = (PersonEntity) this.entityManager.createQuery(jqpl).getSingleResult();
		
		System.out.println("------------  result -----------------");
		System.out.println("person:" + person);
		System.out.println("p2:" + p2);
		System.out.println("p3:" + p3);
		
//		Hibernate: insert into jpa_api_person (age, birthday, birthdayDate, birthdayTime, englishScore, gender, info, mathScore, s_name) values (?, ?, ?, ?, ?, ?, ?, ?, ?)
//		Hibernate: select personenti0_.id as id1_0_, personenti0_.age as age2_0_, personenti0_.birthday as birthday3_0_, personenti0_.birthdayDate as birthday4_0_, personenti0_.birthdayTime as birthday5_0_, personenti0_.englishScore as englishS6_0_, personenti0_.gender as gender7_0_, personenti0_.info as info8_0_, personenti0_.mathScore as mathScor9_0_, personenti0_.s_name as s_name10_0_ from jpa_api_person personenti0_ where personenti0_.id=5
//		Hibernate: select personenti0_.id as id1_0_, personenti0_.age as age2_0_, personenti0_.birthday as birthday3_0_, personenti0_.birthdayDate as birthday4_0_, personenti0_.birthdayTime as birthday5_0_, personenti0_.englishScore as englishS6_0_, personenti0_.gender as gender7_0_, personenti0_.info as info8_0_, personenti0_.mathScore as mathScor9_0_, personenti0_.s_name as s_name10_0_ from jpa_api_person personenti0_ where personenti0_.id=5
//		------------  result -----------------
//		person:PersonEntity [id=5, name=zhangsan, age=20, gender=男, englishScore=20.2, mathScore=89.8, birthdayTime=Wed Feb 03 16:57:58 CST 2016, birthdayDate=Wed Feb 03 16:57:58 CST 2016, birthday=Wed Feb 03 16:57:58 CST 2016, transientProperty=null]
//		p2:PersonEntity [id=5, name=zhangsan, age=20, gender=男, englishScore=20.2, mathScore=89.8, birthdayTime=Wed Feb 03 16:57:58 CST 2016, birthdayDate=Wed Feb 03 16:57:58 CST 2016, birthday=Wed Feb 03 16:57:58 CST 2016, transientProperty=null]
//		p3:PersonEntity [id=5, name=zhangsan, age=20, gender=男, englishScore=20.2, mathScore=89.8, birthdayTime=Wed Feb 03 16:57:58 CST 2016, birthdayDate=Wed Feb 03 16:57:58 CST 2016, birthday=Wed Feb 03 16:57:58 CST 2016, transientProperty=null]

	}
	
	@Test
	public void test_3(){
		PersonEntity person1 = getPersonEntity();
		person1.setName("zhangsan");
		this.entityManager.persist(person1);
		
		String jqpl = "update PersonEntity person set person.name = 'lisi' where id = " + person1.getId();
		this.entityManager.createQuery(jqpl).executeUpdate();
		
		PersonEntity person2 = this.entityManager.find(PersonEntity.class, person1.getId());
		
		System.out.println("person1:id=" +person1.getId() + ", name=" + person1.getName());
		System.out.println("person2:id=" +person2.getId() + ", name=" + person2.getName());
		
	}
}

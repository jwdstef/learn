package org.zgf.learn.jpa.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.junit.Test;
import org.zgf.learn.jpa.base.BasicJPATest;
import org.zgf.learn.jpa.entity.api.Gender;
import org.zgf.learn.jpa.entity.api.PersonEntity;
/**
 * @Description: 主要测试EntityManager类提供的常用接口
 * @author zonggf
 * @date 2015年11月4日-下午3:38:14
 */
public class Test_simple_API extends BasicJPATest {
	/**
	 * JPA 对象有三种状态：
	 * 1. 临时状态： 没有主键id 的对象
	 * 2. 游离状态： 拥有主键id 的对象
	 * 2. 持久化状态： 进行了持久化操作的对象
	 * 
	 * 临时状态 -> 持久化状态： persist();
	 * 持久化状态 -> 游离状态：
	 * 
	 * 
	 */
	
	
	
	/**
	 * Test_API_persist:
	 * 1. 类似于Hibernate 的save方法，将对象由临时状态 转化为 持久化状态
	 * 2. 与Hibernate 区别：在之名id生成策略之后，如果要保存的对象有id值，那么将会抛出异常，不能进行保存；但是，Hibernate可以保存。
	 */
	@Test
	public void testAPI_persist_noId(){
		PersonEntity personEntity = getPersonEntity();
		personEntity.setId(null);
		this.entityManager.persist(personEntity);
	}
	
	//要保存的对象主键id 不为空，因此会抛异常PersistenceException
	@Test(expected=javax.persistence.PersistenceException.class)
	public void testAPI_persist_hasId(){
		PersonEntity personEntity = getPersonEntity();
		personEntity.setId(10000);
		this.entityManager.persist(personEntity);
	}
	
	
	/**
	 * Test_API_find: 根据主键从数据库中查询实体，主键必须非空， 如果不存在则返回null。
	 * 1. 主键必须非空,否则会抛出异常：java.lang.IllegalArgumentException
	 * 2. 类似于Hibernate 中的get方法，根据id查询实体，如果实体不存在，返回null
	 * 3. 与getPreference 方法区别： 立即 加载，执行到getPreference 语句时，立即执行sql语句
	 */
	@Test
	public void testAPI_find(){
		Integer pmKey = 1000;
		PersonEntity personEntity = this.entityManager.find(PersonEntity.class, pmKey);
		if(null != personEntity){
			System.out.println("personEntity:" + personEntity.getClass().getName());
		}else{
			System.out.println("personEntity:" + "null");
		}
		System.out.println("***********  执行sql 时间   *************");
		System.out.println("person:" + personEntity);
		
//		Hibernate: select personenti0_.id as id1_0_0_, personenti0_.age as age2_0_0_, personenti0_.birthday as birthday3_0_0_, personenti0_.birthdayDate as birthday4_0_0_, personenti0_.birthdayTime as birthday5_0_0_, personenti0_.englishScore as englishS6_0_0_, personenti0_.gender as gender7_0_0_, personenti0_.info as info8_0_0_, personenti0_.mathScore as mathScor9_0_0_, personenti0_.s_name as s_name10_0_0_ from jpa_person personenti0_ where personenti0_.id=?
//		personEntity:org.zgf.learn.jpa.entity.simple_api.PersonEntity
//		***********  执行sql 时间   *************
//		PersonEntity [id=1, name=zhangsan, age=20, gender=男, englishScore=20.2, mathScore=89.8, birthdayTime=13:33:20, birthdayDate=2016-01-20, birthday=2016-01-20 13:33:20.0, transientProperty=null]

	}
	
	/**
	 * Test_API_getReference: 从数据库中延时加载实体
	 * 1. 主键必须非空,否则会抛出异常：java.lang.IllegalArgumentException
	 * 2. 类似于Hibernate 的load方法， 如果查询实体不存在，则会跑出异常：javax.persistence.EntityNotFoundException
	 * 3. 区别 find 方法： 采用延时加载，当用到实体的时候，才会向数据库中发送sql语句，因此需要注意有可能产生延时加载异常
	 */
	@Test
	public void testAPI_getReference(){
		Integer pmKey = null;
		PersonEntity personEntity = this.entityManager.getReference(PersonEntity.class, pmKey);
		if(null != personEntity){
			System.out.println("personEntity:" + personEntity.getClass().getName());
		}else{
			System.out.println("personEntity:" + "null");
		}
		System.out.println("***********  执行sql 时间   *************");
		System.out.println("person:" + personEntity);
		
//		personEntity:org.zgf.learn.jpa.entity.simple_api.PersonEntity_$$_jvstbad_0
//		***********  执行sql 时间   *************
//		Hibernate: select personenti0_.id as id1_0_0_, personenti0_.age as age2_0_0_, personenti0_.birthday as birthday3_0_0_, personenti0_.birthdayDate as birthday4_0_0_, personenti0_.birthdayTime as birthday5_0_0_, personenti0_.englishScore as englishS6_0_0_, personenti0_.gender as gender7_0_0_, personenti0_.info as info8_0_0_, personenti0_.mathScore as mathScor9_0_0_, personenti0_.s_name as s_name10_0_0_ from jpa_person personenti0_ where personenti0_.id=?
//		PersonEntity [id=1, name=zhangsan, age=20, gender=男, englishScore=20.2, mathScore=89.8, birthdayTime=13:33:20, birthdayDate=2016-01-20, birthday=2016-01-20 13:33:20.0, transientProperty=null]
	}
	
	
	
	/**
	 * Test_API_remove:将对象从数据库中删除，只能移除持久化对象
	 * 1. 类似于Hibernate 中的delete 方法
	 * 2. 区别于Hibernate 中的delete方法：jpa 只能移除处于持久化 状态的对象，而Hibernate 还可以删除游离状态的对象
	 */
	@Test
	public void testAPI_remove(){
		PersonEntity personEntity = new PersonEntity();
		personEntity.setId(3);
		personEntity = this.entityManager.find(PersonEntity.class, personEntity.getId());
		if(null != personEntity){
			this.entityManager.remove(personEntity);;
		}
	}
	
	//Test_API_remov ：测试remove接口
	@Test(expected=java.lang.IllegalArgumentException.class)
	public void test_remove_wrong(){
		//如果是自己创建的实体 对象，使用remove方式的时候，将会报错
		PersonEntity personEntity = getPersonEntity();
		personEntity.setId(3);
		this.entityManager.remove(personEntity);;
	}
	
	/**
	 * Test_API_merge_1: 保存对象为临时状态
	 *	当merge 对象为临时对象时， jpa 会执行如下操作：
	 *  1. 创建一个新的对象，然后copy 要保存的对象的所有属性
	 *  2. 对新创建的对象进行持久化操作
	 * 
	 *  结果： 执行merge 之后，原对象依然不是持久化对象
	 *
	 */
	@Test
	public void test_merge_1(){
		PersonEntity oldPersonEntity = getPersonEntity();
		oldPersonEntity.setId(null);
		PersonEntity newPersonEntity = this.entityManager.merge(oldPersonEntity);
		System.out.println("oldPersonEntity.id:" + oldPersonEntity.getId());
		System.out.println("newPersonEntity.id:" + newPersonEntity.getId());
		
//		
//		Hibernate: insert into jpa_person (age, birthday, birthdayDate, birthdayTime, englishScore, gender, info, mathScore, s_name) values (?, ?, ?, ?, ?, ?, ?, ?, ?)
//		oldPersonEntity.id:null
//		newPersonEntity.id:1
	}
	
	/**
	 * Test_API_merge_1: 
	 * 前提条件：
	 * 	1. 原对象为 游离对象（有OId）
	 *  2. em 缓存中不存在id=OId 的对象
	 *  3. 数据库中也不存在id=OId 的对象
	 *  
	 * 执行操作：
	 * 	1. 检查em 缓存中是否存在id=OId 的对象
	 *  2. 如果em 缓存中不存在id=OId 的对象，则向数据库中查询
	 *  3. 如果数据库 中也不存在id=OId 的记录，那么将执行操作和 临时对象操作一样
	 *     -> 创建一个新的对象，copy 原对象非id 属性
	 *     -> 对 新对象执行 持久化 操作
	 *     
	 *  结果： 执行merge 之后，原对象依然不是持久化对象
	 */
	@Test
	public void test_merge_2(){
		PersonEntity oldPersonEntity = getPersonEntity();
		oldPersonEntity.setId(123);
		PersonEntity newPersonEntity = this.entityManager.merge(oldPersonEntity);
		System.out.println("oldPersonEntity.id:" + oldPersonEntity.getId());
		System.out.println("newPersonEntity.id:" + newPersonEntity.getId());
		
		
//		结果分析： 执行了两条sql 语句，一条是向数据库中查询，一条是insert 
//		从执行结果可以看出，如果原对象为持久化对象的话，那么原对象id 应该为2 ，而不是123
//		Hibernate: select personenti0_.id as id1_0_0_, personenti0_.age as age2_0_0_, personenti0_.birthday as birthday3_0_0_, personenti0_.birthdayDate as birthday4_0_0_, personenti0_.birthdayTime as birthday5_0_0_, personenti0_.englishScore as englishS6_0_0_, personenti0_.gender as gender7_0_0_, personenti0_.info as info8_0_0_, personenti0_.mathScore as mathScor9_0_0_, personenti0_.s_name as s_name10_0_0_ from jpa_person personenti0_ where personenti0_.id=?
//		Hibernate: insert into jpa_person (age, birthday, birthdayDate, birthdayTime, englishScore, gender, info, mathScore, s_name) values (?, ?, ?, ?, ?, ?, ?, ?, ?)
//		oldPersonEntity.id:123
//		newPersonEntity.id:2
	}
	
	
	/**
	 * Test_API_merge_3: 
	 * 前提条件：
	 * 	1. 原对象为 游离对象（有OId）
	 *  2. em 缓存中不存在id=OId 的对象
	 *  3. 数据库中存在id=OId 的对象
	 *  
	 * 执行操作：
	 * 	1. 检查em 缓存中是否存在id=OId 的对象
	 *  2. 如果em 缓存中不存在id=OId 的对象，则向数据库中查询
	 *  3. 如果数据库 中存在id=OId 的记录，那么将执行如下操作：
	 *     -> 将原对象的属性copy 到从数据中查询到的对象上
	 *     -> 对数据库中查询到的对象进行update 操作
	 *     
	 *  结果： 执行merge 之后，原对象依然不是持久化对象
	 */
	@Test
	public void test_merge_3(){
		PersonEntity oldPersonEntity = getPersonEntity();
		oldPersonEntity.setId(1);
		PersonEntity newPersonEntity = this.entityManager.merge(oldPersonEntity);
		System.out.println(oldPersonEntity == newPersonEntity);
		System.out.println("oldPersonEntity.id:" + oldPersonEntity.getId());
		System.out.println("newPersonEntity.id:" + newPersonEntity.getId());
		
//		结果分析：执行了两条sql 语句，一条查询 ，一条update
//		Hibernate: select personenti0_.id as id1_0_0_, personenti0_.age as age2_0_0_, personenti0_.birthday as birthday3_0_0_, personenti0_.birthdayDate as birthday4_0_0_, personenti0_.birthdayTime as birthday5_0_0_, personenti0_.englishScore as englishS6_0_0_, personenti0_.gender as gender7_0_0_, personenti0_.info as info8_0_0_, personenti0_.mathScore as mathScor9_0_0_, personenti0_.s_name as s_name10_0_0_ from jpa_person personenti0_ where personenti0_.id=?
//		false
//		oldPersonEntity.id:1
//		newPersonEntity.id:1
//		Hibernate: update jpa_person set age=?, birthday=?, birthdayDate=?, birthdayTime=?, englishScore=?, gender=?, info=?, mathScore=?, s_name=? where id=?

	}
	
	/**
	 * Test_API_merge_3: 
	 * 前提条件：
	 * 	1. 原对象为 游离对象（有OId）
	 *  2. em 缓存中存在id=OId 的对象
	 *  3. 将原对象属性copy 到em 缓存中的 对象
	 *  4. 对em 缓存中的对象进行update 操作
	 *  
	 * 执行操作：
	 * 	1. 检查em 缓存中是否存在id=OId 的对象
	 *  2. 如果em 缓存中存在id=OId 的对象，则将原对象属性copy 到em 缓存中的对象上
	 *  3. 对em 缓存中的对象进行update 操作
	 *     
	 *  结果： 执行merge 之后，原对象依然不是持久化对象
	 */
	@Test
	public void test_merge_4(){
		Integer exsistId = 1;
		
		PersonEntity oldPersonEntity = new PersonEntity();
		oldPersonEntity.setId(exsistId);
		oldPersonEntity.setName("gaofeng");
		
		PersonEntity emPersonEntity = this.entityManager.find(PersonEntity.class, exsistId);
		System.out.println("***  之前执行的sql 为向em 缓存中构造缓存，不进行结果分析     ****");
		
		System.out.println("before Mege:");
		System.out.println("oldPersonEntity:" + oldPersonEntity);
		System.out.println("emPersonEntity:" + emPersonEntity);
		this.entityManager.merge(oldPersonEntity);
		System.out.println("After Mege:");
		System.out.println("oldPersonEntity:" + oldPersonEntity);
		System.out.println("emPersonEntity:" + emPersonEntity);
		
		try{
			this.entityManager.remove(oldPersonEntity);
		}catch(java.lang.IllegalArgumentException ex){
			System.out.println("oldPersonEntity 不能调用remove 方法，证明oldPersonEntity 不是持久化对象，因为remove 只能删除持久化对象");
		}
		
//		结果分析：
//		1. 只执行了一条update 语句
//		2. 程序能正常运行，证明原对象oldPersonEntity 没有 被纳入到em 缓存中（因为em 缓存中，同一实体类不能存在两个相同的对象），
//		       也就可以说明：虽然代码写的是merge(oldPersonEntity)， 但是执行的不是update(oldPersonEntity), 但是执行的确是update(emPersonEntity)

//		Hibernate: select personenti0_.id as id1_0_0_, personenti0_.age as age2_0_0_, personenti0_.birthday as birthday3_0_0_, personenti0_.birthdayDate as birthday4_0_0_, personenti0_.birthdayTime as birthday5_0_0_, personenti0_.englishScore as englishS6_0_0_, personenti0_.gender as gender7_0_0_, personenti0_.info as info8_0_0_, personenti0_.mathScore as mathScor9_0_0_, personenti0_.s_name as s_name10_0_0_ from jpa_person personenti0_ where personenti0_.id=?
//		***  之前执行的sql 为向em 缓存中构造缓存，不进行结果分析     ****
//		before Mege:
//		oldPersonEntity:PersonEntity [id=1, name=gaofeng, age=0, gender=null, englishScore=null, mathScore=null, birthdayTime=null, birthdayDate=null, birthday=null, transientProperty=null]
//		emPersonEntity:PersonEntity [id=1, name=zhangsan, age=20, gender=男, englishScore=20.2, mathScore=89.8, birthdayTime=15:36:16, birthdayDate=2016-01-20, birthday=2016-01-20 15:36:16.0, transientProperty=null]
//		After Mege:
//		oldPersonEntity:PersonEntity [id=1, name=gaofeng, age=0, gender=null, englishScore=null, mathScore=null, birthdayTime=null, birthdayDate=null, birthday=null, transientProperty=null]
//		emPersonEntity:PersonEntity [id=1, name=gaofeng, age=0, gender=null, englishScore=null, mathScore=null, birthdayTime=null, birthdayDate=null, birthday=null, transientProperty=null]
//		oldPersonEntity 不能调用remove 方法，证明oldPersonEntity 不是持久化对象，因为remove 只能删除持久化对象

	}
	
	//merge 类似于Hibernate 中的saveOrUpdate 方法，但是执行的机制缺失不相同的， JPA 是copy对象属性，而Hibernate 是将对象纳入 Session 缓存
	
	
	/** Test_API_flush: 执行update 语句, 将em 一级缓存中所有有变化的实体更新到数据库中
	 * 默认情况下，事务提交时会执行sql 语句，然后再提交事务。
	 * 如果手动执行flush 操作的话，会在执行flush 方法是就执行 sql 语句， 而不是等到事务提交时再执行 sql语句。
	 * 
	 * 执行sql语句的同时，数据库会对更改的记录上锁，事务提交完毕之后 ，才会解锁；注意是对sql影响的记录上锁，而不是对整个表上锁。 
	 * 也就是说，在执行sql 之后，事务提交之前，其它途径只能对这条记录进行读，不能进行修改，必须等到此记录解锁之后才能进行操作。
	 * 
	 * 默认情况下，提交sql和执行事务依次执行，记录锁的时间比较短，如果采用手工flush 的方式，可能造成锁的时间增长。
	 */
	@Test
	public void testAPI_flush(){
		
		PersonEntity personEntity = this.entityManager.find(PersonEntity.class, 1);
		personEntity.setName(personEntity.getName() + "_flush");
		
		PersonEntity personEntity2 = this.entityManager.find(PersonEntity.class, 2);
		personEntity2.setName(personEntity2.getName() + "_flush");
		
		PersonEntity personEntity3 = this.entityManager.find(PersonEntity.class, 3);
		
		System.out.println("**********  执行flush 方法 之前   ************");
		this.entityManager.flush();
		//可在下行添加断点
		System.out.println("**********  执行flush 方法 之后   ************");
		
//		结果分析：执行flush 方法时，会执行sql语句，但是此时数据 库表中并没有进行更改，到事务提交的时候，才进行了更改
//		Hibernate: select personenti0_.id as id1_0_0_, personenti0_.age as age2_0_0_, personenti0_.birthday as birthday3_0_0_, personenti0_.birthdayDate as birthday4_0_0_, personenti0_.birthdayTime as birthday5_0_0_, personenti0_.englishScore as englishS6_0_0_, personenti0_.gender as gender7_0_0_, personenti0_.info as info8_0_0_, personenti0_.mathScore as mathScor9_0_0_, personenti0_.s_name as s_name10_0_0_ from jpa_person personenti0_ where personenti0_.id=?
//		Hibernate: select personenti0_.id as id1_0_0_, personenti0_.age as age2_0_0_, personenti0_.birthday as birthday3_0_0_, personenti0_.birthdayDate as birthday4_0_0_, personenti0_.birthdayTime as birthday5_0_0_, personenti0_.englishScore as englishS6_0_0_, personenti0_.gender as gender7_0_0_, personenti0_.info as info8_0_0_, personenti0_.mathScore as mathScor9_0_0_, personenti0_.s_name as s_name10_0_0_ from jpa_person personenti0_ where personenti0_.id=?
//		Hibernate: select personenti0_.id as id1_0_0_, personenti0_.age as age2_0_0_, personenti0_.birthday as birthday3_0_0_, personenti0_.birthdayDate as birthday4_0_0_, personenti0_.birthdayTime as birthday5_0_0_, personenti0_.englishScore as englishS6_0_0_, personenti0_.gender as gender7_0_0_, personenti0_.info as info8_0_0_, personenti0_.mathScore as mathScor9_0_0_, personenti0_.s_name as s_name10_0_0_ from jpa_person personenti0_ where personenti0_.id=?
//		**********  执行flush 方法 之前   ************
//		Hibernate: update jpa_person set age=?, birthday=?, birthdayDate=?, birthdayTime=?, englishScore=?, gender=?, info=?, mathScore=?, s_name=? where id=?
//		Hibernate: update jpa_person set age=?, birthday=?, birthdayDate=?, birthdayTime=?, englishScore=?, gender=?, info=?, mathScore=?, s_name=? where id=?
//		**********  执行flush 方法 之后   ************

	}
	
	@Test
	public void testAPI_flush_no(){
		
		PersonEntity personEntity = this.entityManager.find(PersonEntity.class, 1);
		personEntity.setName(personEntity.getName() + "_flush");
		
		PersonEntity personEntity2 = this.entityManager.find(PersonEntity.class, 2);
		personEntity2.setName(personEntity2.getName() + "_flush");
		
		PersonEntity personEntity3 = this.entityManager.find(PersonEntity.class, 3);
		
		System.out.println("**********  执行flush 方法 之前   ************");
		System.out.println("**********  执行flush 方法 之后   ************");
		
//		默认情况下在事务提交时，才执行sql 语句
//		Hibernate: select personenti0_.id as id1_0_0_, personenti0_.age as age2_0_0_, personenti0_.birthday as birthday3_0_0_, personenti0_.birthdayDate as birthday4_0_0_, personenti0_.birthdayTime as birthday5_0_0_, personenti0_.englishScore as englishS6_0_0_, personenti0_.gender as gender7_0_0_, personenti0_.info as info8_0_0_, personenti0_.mathScore as mathScor9_0_0_, personenti0_.s_name as s_name10_0_0_ from jpa_person personenti0_ where personenti0_.id=?
//		Hibernate: select personenti0_.id as id1_0_0_, personenti0_.age as age2_0_0_, personenti0_.birthday as birthday3_0_0_, personenti0_.birthdayDate as birthday4_0_0_, personenti0_.birthdayTime as birthday5_0_0_, personenti0_.englishScore as englishS6_0_0_, personenti0_.gender as gender7_0_0_, personenti0_.info as info8_0_0_, personenti0_.mathScore as mathScor9_0_0_, personenti0_.s_name as s_name10_0_0_ from jpa_person personenti0_ where personenti0_.id=?
//		Hibernate: select personenti0_.id as id1_0_0_, personenti0_.age as age2_0_0_, personenti0_.birthday as birthday3_0_0_, personenti0_.birthdayDate as birthday4_0_0_, personenti0_.birthdayTime as birthday5_0_0_, personenti0_.englishScore as englishS6_0_0_, personenti0_.gender as gender7_0_0_, personenti0_.info as info8_0_0_, personenti0_.mathScore as mathScor9_0_0_, personenti0_.s_name as s_name10_0_0_ from jpa_person personenti0_ where personenti0_.id=?
//		**********  执行flush 方法 之前   ************
//		**********  执行flush 方法 之后   ************
//		Hibernate: update jpa_person set age=?, birthday=?, birthdayDate=?, birthdayTime=?, englishScore=?, gender=?, info=?, mathScore=?, s_name=? where id=?
//		Hibernate: update jpa_person set age=?, birthday=?, birthdayDate=?, birthdayTime=?, englishScore=?, gender=?, info=?, mathScore=?, s_name=? where id=?

	}
	
	
	/*** Test_API_refresh: 执行select 语句, 从数据库中查询对象最新信息，更新em 一级缓存中的对象
	 *   注意：只能refresh 持久化 对象
	 *   
	 *   refresh 和 flush 异同点：
	 *   相同: 都是为了使数据库中实体和em 一级缓存中实体保持一致
	 *   不同：1. 数据依据标准不一致：
	 *   		refresh 以数据库为准，执行select 语句 ；
	 *   		flush 以em 一级缓存为主，执行 update语句;
	 *   	 2. 作用域不同：
	 *   		refresh 一次只能查询一个实体；
	 *          flush 一次会更新em 中所有变化了的实体
	 */
	@Test
	public void testAPI_refresh(){
		PersonEntity personEntity = this.entityManager.find(PersonEntity.class, 1);
		personEntity.setName(personEntity.getName() + "_refresh");
		System.out.println("pesonEntity.name:" + personEntity.getName());
		System.out.println("**********  执行reflush 方法 之前   ************");
		
		this.entityManager.refresh(personEntity);
		System.out.println("pesonEntity.name:" + personEntity.getName());
		
//		Hibernate: select personenti0_.id as id1_0_0_, personenti0_.age as age2_0_0_, personenti0_.birthday as birthday3_0_0_, personenti0_.birthdayDate as birthday4_0_0_, personenti0_.birthdayTime as birthday5_0_0_, personenti0_.englishScore as englishS6_0_0_, personenti0_.gender as gender7_0_0_, personenti0_.info as info8_0_0_, personenti0_.mathScore as mathScor9_0_0_, personenti0_.s_name as s_name10_0_0_ from jpa_person personenti0_ where personenti0_.id=?
//		pesonEntity.name:zhangsan_测试测试_refresh
//		**********  执行reflush 方法 之前   ************
//		Hibernate: select personenti0_.id as id1_0_0_, personenti0_.age as age2_0_0_, personenti0_.birthday as birthday3_0_0_, personenti0_.birthdayDate as birthday4_0_0_, personenti0_.birthdayTime as birthday5_0_0_, personenti0_.englishScore as englishS6_0_0_, personenti0_.gender as gender7_0_0_, personenti0_.info as info8_0_0_, personenti0_.mathScore as mathScor9_0_0_, personenti0_.s_name as s_name10_0_0_ from jpa_person personenti0_ where personenti0_.id=?
//		pesonEntity.name:zhangsan_测试测试
	}
	
	//refresh 非持久化对象会抛出异常
	@Test(expected=java.lang.IllegalArgumentException.class)
	public void testAPI_refresh_wrong(){
		PersonEntity personEntity = new PersonEntity();
		personEntity.setId(1);
		this.entityManager.refresh(personEntity);
		//java.lang.IllegalArgumentException: Entity not managed
	}
	
	/** 构造PersonEntity对象    */
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
	
	
}

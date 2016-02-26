package org.zgf.spring.annotation.tx;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:org/zgf/spring/annotation/tx/applications-tx.xml")
public class Test_01_tx {
	
	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private AccountDao accountDao;
	
	@Before
	public void tearDown(){
		this.accountDao.updateMoney(1, 1000);
		this.accountDao.updateMoney(2, 800);
		System.out.println("********  数据初始化成功   **********");
		System.out.println("********  id:1 , money:1000   **********");
		System.out.println("********  id:1 , money:800   **********");
		
	}
	
	@Test
	public void test_init_add(){
		Account account = new Account(102, 1000);
		this.accountDao.addAccount(account);
		
		Account account2 = new Account(102, 800);
		this.accountDao.addAccount(account2);
	}
	
	
	@Test
	public void test_sub(){
		int id1 = 1;
		int id2 = 2;
		int subMoney = 1000;
		
		this.accountService.subMoney(id1, id2, subMoney);
	}
	
	@Test
	public void test_tx(){
		int id1 = 1;
		int id2 = 2;
		int subMoney = 1000;
		
		this.accountService.callSubMoneyNoTx(id1, id2, subMoney);
	}
	
	int id = 1;
	int money = 1000;
	
	/** 1. 不添加@Transaction 注解， 发生异常数据不回滚 **/
	@Test
	public void test_1(){
		this.accountService.subMoney(id, money);
	}
	
	/** 2. 添加@Transaction 注解， 发生异常数据回滚 **/
	@Test
	public void test_2(){
		try {
			this.accountService.subMoney2(id, money);
		} catch (Exception e) {
			System.out.println("发生异常了。。。");
		}
	}
	
	/** 3. 添加@Transaction 标签， 设置只读, 将不能提交事务 **/
	@Test
	public void test_3(){
		this.accountService.subMoney3(id, money);
	}
	
	/** 4. 添加@Transaction 标签， 设置超时时间, 超出时间，强制回滚 **/
	@Test
	public void test_4(){
		this.accountService.subMoney4(id, money);
	}
	
	/** 4. 添加@Transaction 标签， 设置超时时间, 超出时间，强制回滚 **/
	@Test
	public void test_5(){
		this.accountService.subMoney5(id, money);
	}
	
	/** 4. 添加@Transaction 标签， 设置超时时间, 超出时间，强制回滚 **/
	@Test
	public void test_6(){
		this.accountService.subMoney6(id, money);
	}
	
	@Test
	public void test_61(){
		this.accountService.doService61(id, money);
	}
	
	
	
	
	
	
}

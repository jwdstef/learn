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
	
	@After
	public void tearDown(){
		System.out.println("********  结束   **********");
	}
	
	@Before
	public void setUp(){
		this.accountService.updateMoney(1, 1000);
		this.accountService.updateMoney(2, 800);
	}
	
	@Test
	public void test_init_add(){
		Account account = new Account(102, 1000);
		this.accountService.addAccount(account);
		
		Account account2 = new Account(102, 800);
		this.accountService.addAccount(account2);
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
	
}

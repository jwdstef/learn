package org.zgf.spring.annotation.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService implements IAccountService{
	
	@Autowired
	private AccountDao accountDao;
	
	@Transactional(propagation= Propagation.REQUIRES_NEW)
	@Override
	public void subMoney(int id1, int id2, int money) {
		accountDao.subAccountMoney(id1, money);
		accountDao.subAccountMoney(id2, money);
	}
	
	@Transactional
	@Override
	public void callSubMoneyTx(int id1, int id2, int money) {
		this.subMoney(id1, id2, money);
		
		throw new AccountException("手动异常...");
	}
	
	/**
	 * 最外层方法不设置事务
	 * 	1. 里面设置事务, 事务传播级别为 默认的Propagation.REQUIRED
	 *  2. 里面设置事务, 事务传播级别为 Propagation.REQUIRES_NEW
	 */
	@Override
	public void callSubMoneyNoTx(int id1, int id2, int money) {
		this.subMoney(id1, id2, money);
		throw new AccountException("手动异常...");
	}
	
	
	@Override
	public void subMoney(int id, int money) {
		this.accountDao.subAccountMoney(id, money);
		throw new AccountException("数据库异常，数据应该回滚...");
	}
	
	@Transactional
	@Override
	public void subMoney2(int id, int money) throws Exception {
		this.accountDao.subAccountMoney(id, money);
		//throw new AccountException("数据库异常，数据应该回滚...");
		throw new Exception("数据库异常，数据应该不回滚...");
	}
	
	@Transactional(readOnly = true)
	@Override
	public void subMoney3(int id, int money) {
		this.accountDao.subAccountMoney(id, money);
	}
	
	@Transactional(timeout=3)
	@Override
	public void subMoney4(int id, int money) {
		this.accountDao.subAccountMoney(id, money);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void subMoney5(int id, int money) {
		this.doService51(id, money);
	}
	
	@Transactional
	public void doService51(int id, int money){
		this.accountDao.subAccountMoney(id, money);
		throw new AccountException("数据库异常，数据应该回滚...");
	}
	
	
	@Override
	public void subMoney6(int id, int money) {
		this.doService61(id, money);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void doService61(int id, int money){
		this.accountDao.subAccountMoney(id, money);
		throw new AccountException("数据库异常，数据应该回滚...");
	}
	
}

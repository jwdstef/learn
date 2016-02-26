package org.zgf.spring.annotation.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService implements IAccountService{
	
	@Autowired
	private AccountDao accountDao;
	
	@Override
	public void addAccount(Account account) {
		accountDao.addAccount(account);
	}

	@Override
	public Account getAccountById(int id) {
		return accountDao.getAccountById(id);
	}

	@Override
	public void subMoney(int id, int money) {
		accountDao.subAccountMoney(id, money);
	}

	@Override
	public void updateMoney(int id, int money) {
		accountDao.updateMoney(id, money);
	}
	
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
	
	
	
}

package org.zgf.spring.annotation.tx;

public interface IAccountService {
	
	//增加账户信息
	public void addAccount(Account account);
	
	//根据id查询账户信息
	public Account getAccountById(int id);
	
	//取款
	public void subMoney(int id, int money);
	
	//更新钱
	public void updateMoney(int id, int money);

	//减钱
	public void subMoney(int id1, int id2, int money);
	
	public void callSubMoneyTx(int id1, int id2, int money);
	
	public void callSubMoneyNoTx(int id1, int id2, int money);
	
}

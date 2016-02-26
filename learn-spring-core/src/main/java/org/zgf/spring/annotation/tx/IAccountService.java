package org.zgf.spring.annotation.tx;

public interface IAccountService {
	
	//减钱
	public void subMoney(int id1, int id2, int money);
	
	public void callSubMoneyTx(int id1, int id2, int money);
	
	public void callSubMoneyNoTx(int id1, int id2, int money);
	
	
	
	//1. 不添加@Transaction 标签，发生异常时，数据库事务不回滚
	public void subMoney(int id, int money);
	
	
	//2. 添加@Transaction 标签， 发生异常时，数据库事务回滚
	public void subMoney2(int id, int money)throws Exception;
	
	//3. 添加@Transaction 标签， 设置只读
	public void subMoney3(int id, int money);
	
	//4. 添加@Transaction 标签， 设置超时时间，超过时间强制回滚//TODO
	public void subMoney4(int id, int money);
	
	//5. 一级方法不添加事务注解，二级方法添加默认事务注解，发生异常，数据不回滚，因为事务开启不成功
	public void subMoney5(int id, int money);
	
	public void doService51(int id, int money);
	
	//6. 一级方法不添加事务注解，二级方法添加默认事务注解，发生异常，数据不回滚，因为事务开启不成功
	public void subMoney6(int id, int money);
	
	public void doService61(int id, int money);
}

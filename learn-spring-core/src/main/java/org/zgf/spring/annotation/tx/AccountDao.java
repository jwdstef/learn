package org.zgf.spring.annotation.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String tableName = " spring_tx_account ";
	
	public void addAccount(Account account){
		
		String sql = "insert into" + tableName + "values(null,?,?)";
		this.jdbcTemplate.update(sql, account.getNumber(), account.getMoney());
	}
	
	/**
	 * 根据id 获取账户信息
	 */
	public Account getAccountById(int id){
		String sql = "select * from " + tableName + " where id = ?";
		
		RowMapper<Account> rowMapper = new BeanPropertyRowMapper<>(Account.class);
		return this.jdbcTemplate.queryForObject(sql, rowMapper,id);
	}
	
	
	/**
	 * 账户余额减
	 */
	public void subAccountMoney(int id, int money){
		Account account = getAccountById(id);
		
		if(money < 0 ){
			throw new AccountException("减去的金额不能小于0");
		}
		
		if(account.getMoney() < money){
			throw new AccountException("余额不足！");
		}else{
			updateMoney(id, account.getMoney()-money);
		}
	}

	/**
	 * 更新账户余额
	 */
	public void updateMoney(int id, int money) {
		String sql = "update " + tableName + " set  money = ? where id = ?";
		this.jdbcTemplate.update(sql, money, id);
	}

}

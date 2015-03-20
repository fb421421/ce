package com.gaming.ce.finacial.repository.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gaming.ce.finacial.repository.WalletRepository;

@Repository
public class WalletRepositoryImpl implements WalletRepository{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void increaseBalance(Long userId, BigDecimal amount) {
		 jdbcTemplate.update("UPDATE WHERE SET balance=balance+? WHERE id=? ", amount , userId );
	}

	@Override
	public void decreaseBalance(Long userId, BigDecimal amount) {
		jdbcTemplate.update("UPDATE WHERE SET balance=balance-? WHERE id=? ", amount , userId );
	}

	@Override
	public BigDecimal findBalanceByUserId(Long userId) {
		List<Map<String, Object>> list = jdbcTemplate.queryForList(
				"SELECT balance FROM Wallet WHERE userId=? ", userId );
		if( list!=null && list.size()>0){
			return (BigDecimal) list.get(0).get("balance");
		}else{
			return null;
		}
	}
	
	@Override
	public BigDecimal createWalletByUserId( Long userId ){
		String sql = "INSERT INTO Wallet "
				+ "( userId, balance ) VALUES (?,?)";
		BigDecimal initBalance = new BigDecimal("0.00");
		jdbcTemplate.update(sql,
				new Object[] { userId,initBalance});
		return initBalance;
	}

}

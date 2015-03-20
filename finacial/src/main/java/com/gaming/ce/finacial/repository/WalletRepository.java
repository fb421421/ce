package com.gaming.ce.finacial.repository;

import java.math.BigDecimal;


public interface WalletRepository {
	
	public BigDecimal createWalletByUserId( Long userId );

	void increaseBalance(Long userId, BigDecimal amount);

	void decreaseBalance(Long userId, BigDecimal amount);

	BigDecimal findBalanceByUserId(Long userId);
	
}


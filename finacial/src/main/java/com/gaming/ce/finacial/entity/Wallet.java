package com.gaming.ce.finacial.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Wallet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1129648589052943971L;

	private Long userId;
	
	private BigDecimal balance;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
}

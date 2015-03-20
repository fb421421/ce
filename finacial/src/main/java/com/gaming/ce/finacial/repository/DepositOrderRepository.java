package com.gaming.ce.finacial.repository;

import java.math.BigDecimal;


public interface DepositOrderRepository {

	BigDecimal findDepositOrderAmountById(String depositOrderId);

	void updateDepositOrderStatus(Long userId, String depositOrderId);
	
}


package com.gaming.ce.finacial.usecase.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaming.ce.finacial.repository.DepositOrderRepository;
import com.gaming.ce.finacial.usecase.BalanceService;
import com.gaming.ce.finacial.usecase.DepositOrderService;

@Service
@Transactional
public class DepositOrderServiceImpl implements DepositOrderService {

	@Autowired
	private DepositOrderRepository depositOrderRepository;

	@Autowired
	private BalanceService balanceService;

	@Override
	public void auditDepositOrder(Long userId, String depositOrderId) {

		BigDecimal amount = depositOrderRepository
				.findDepositOrderAmountById(depositOrderId);

		depositOrderRepository.updateDepositOrderStatus( userId , depositOrderId );

		balanceService.increaseBalance(userId, amount);

	}

}

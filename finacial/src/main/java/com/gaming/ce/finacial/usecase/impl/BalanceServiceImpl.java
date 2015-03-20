package com.gaming.ce.finacial.usecase.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaming.ce.common.constant.Code;
import com.gaming.ce.finacial.repository.WalletRepository;
import com.gaming.ce.finacial.usecase.BalanceService;

@Service
@Transactional
public class BalanceServiceImpl implements BalanceService{
	
	
	@Autowired
	private WalletRepository walletRepository;

	@Override
	public int increaseBalance(Long userId, BigDecimal amount) {
		
		walletRepository.increaseBalance(userId,amount);
		
		return Code.SUCCESS;
	}

	@Override
	public int decreaseBalance(Long userId, BigDecimal amount) {
		
		walletRepository.decreaseBalance(userId,amount);
		
		return Code.SUCCESS;
	}

	@Override
	public BigDecimal findBalance(Long userId) {
		
		BigDecimal balance =  walletRepository.findBalanceByUserId(userId);
		
		/*如果余额为空，则创建钱包，返回初始余额*/
		if( balance == null ){
			balance = walletRepository.createWalletByUserId(userId);
		}
		
		return balance;
	}

}

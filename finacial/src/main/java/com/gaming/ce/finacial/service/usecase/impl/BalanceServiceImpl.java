package com.gaming.ce.finacial.service.usecase.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaming.ce.common.Code;
import com.gaming.ce.finacial.entity.user.Wallet;
import com.gaming.ce.finacial.repository.user.WalletRepository;
import com.gaming.ce.finacial.service.usecase.BalanceService;

@Service
@Transactional
public class BalanceServiceImpl implements BalanceService{
	
	
	@Autowired
	private WalletRepository walletRepository;

	@Override
	public int increaseBalance(Long userId, BigDecimal amount) {
		
		Wallet wallet = walletRepository.findWalletWithLockByUserId(userId);
		wallet.setBalance(wallet.getBalance().add(amount));
		
		walletRepository.save(wallet);
		
		return Code.SUCCESS;
	}

	@Override
	public int decreaseBalance(Long userId, BigDecimal amount) {
		
		Wallet wallet = walletRepository.findWalletWithLockByUserId(userId);
		wallet.setBalance(wallet.getBalance().subtract(amount));
		
		walletRepository.save(wallet);
		
		return Code.SUCCESS;
	}

	@Override
	public BigDecimal findBalance(Long userId) {
		Wallet wallet = walletRepository.findOne(userId);
		return wallet.getBalance();
	}

}

package com.gaming.ce.finacial.repository.user;


import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.gaming.ce.finacial.entity.user.Wallet;




public interface WalletRepository extends JpaRepository<Wallet,Long>{
	
	@Lock(LockModeType.OPTIMISTIC)
	public Wallet findWalletWithLockByUserId(Long userId);
}


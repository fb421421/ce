package com.gaming.ce.finacial.repository.order;



import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gaming.ce.finacial.entity.order.DepositOrder;





public interface DepositOrderRepository extends CrudRepository<DepositOrder,String> {
	
	public List<DepositOrder> findDepositOrderByUserId( Long userId );
	
}


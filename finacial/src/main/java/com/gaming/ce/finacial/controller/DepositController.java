package com.gaming.ce.finacial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaming.ce.finacial.repository.DepositOrderRepository;
import com.gaming.ce.finacial.usecase.DepositOrderService;
import com.gaming.ce.server.service.SessionService;
import com.wordnik.swagger.annotations.Api;


@RestController
@RequestMapping("/deposit")
@Api(value = "/deposit", description = "存款")
public class DepositController {
	
	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private DepositOrderService depositOrderService;
	
	@Autowired
	private DepositOrderRepository depositOrderRepository;
	
	
	/*@RequestMapping(value = "/depositOrder", method = RequestMethod.POST)
	@ApiOperation(value="创建存款订单")
	@RolesAllowed(Role.USER)
	public ResponseEntity<Void> deposit(
			@ApiParam( required = true, value = "金额") @RequestParam @NotNull @Min(value = 0) BigDecimal amount,
			@ApiIgnore HttpServletRequest request,
			@ApiIgnore @CookieValue(TokenUtil.TOKEN_COOKIE_NMAE) String token) {
		
		
		Session session = sessionService.findSessionByToken(token);
		
		DepositOrder depositOrder = new DepositOrder();
		depositOrder.setUserId(session.getUserId());
		depositOrder.setUserName(session.getUserName());
		depositOrder.setStatus(0);
		depositOrder.setAmount(amount);
		depositOrder.setCreateTime(new Date());
		
		depositOrderRepository.save(depositOrder);

		return  ResponseEntity.ok().build();
	}
	
	@RequestMapping(value = "/depositOrder", method = RequestMethod.GET)
	@ApiOperation(value="查询存款订单")
	@RolesAllowed(Role.USER)
	public List<DepositOrder> findDepositOrderByUserId(
			@ApiIgnore HttpServletRequest request,
			@ApiIgnore @CookieValue(TokenUtil.TOKEN_COOKIE_NMAE) String token) {
		
		Session session = sessionService.findSessionByToken(token);
		List<DepositOrder> depositOrders = depositOrderRepository.findDepositOrderByUserId(session.getUserId());
		
		return  depositOrders;
	}
	
	@RequestMapping(value = "/depositOrder", method = RequestMethod.PUT)
	@ApiOperation(value="审核存款订单")
	public void auditDepositOrder(
			@ApiParam( required = true, value = "存款订单ID") @RequestParam String depositOrderId,
			HttpServletRequest request) {
		
		depositOrderService.auditDepositOrder(depositOrderId);

	}*/

}

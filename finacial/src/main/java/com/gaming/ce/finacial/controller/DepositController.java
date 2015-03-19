package com.gaming.ce.finacial.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaming.ce.common.Role;
import com.gaming.ce.finacial.entity.order.DepositOrder;
import com.gaming.ce.finacial.repository.order.DepositOrderRepository;
import com.gaming.ce.finacial.service.usecase.DepositOrderService;
import com.gaming.ce.server.entity.Session;
import com.gaming.ce.server.service.SessionService;
import com.gaming.ce.server.util.TokenUtil;
import com.gaming.ce.user.design.service.UserService;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;


@RestController
@RequestMapping("/deposit")
@Api(value = "/deposit", description = "存款")
public class DepositController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private DepositOrderService depositOrderService;
	
	@Autowired
	private DepositOrderRepository depositOrderRepository;
	
	
	@RequestMapping(value = "/depositOrder", method = RequestMethod.POST)
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

	}

}

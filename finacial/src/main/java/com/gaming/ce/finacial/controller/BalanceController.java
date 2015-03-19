package com.gaming.ce.finacial.controller;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gaming.ce.common.Code;
import com.gaming.ce.common.Role;
import com.gaming.ce.finacial.service.usecase.BalanceService;
import com.gaming.ce.server.entity.Session;
import com.gaming.ce.server.service.SessionService;
import com.gaming.ce.server.util.CookieUtil;
import com.gaming.ce.server.util.TokenUtil;
import com.gaming.ce.user.design.service.UserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/balance")
@Api(value = "/balance", description = "余额")
public class BalanceController {
	
	@Autowired
	private BalanceService balanceService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SessionService sessionService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ApiOperation(value="余额查询")
	@RolesAllowed(Role.USER)
	public Code findBalance(
			HttpServletRequest request) {
		
		Session session = sessionService.findSessionByToken(CookieUtil.getCookie(request, TokenUtil.TOKEN_COOKIE_NMAE));
		
		Code code = new Code(Code.SUCCESS);
		code.setMessage(balanceService.findBalance(session.getUserId()).toString());
		
		return  code;
	}
}

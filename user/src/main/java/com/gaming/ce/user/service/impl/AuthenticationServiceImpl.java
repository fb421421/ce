package com.gaming.ce.user.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaming.ce.common.Code;
import com.gaming.ce.server.entity.Session;
import com.gaming.ce.server.service.Authenticatior;
import com.gaming.ce.server.service.Authorization;
import com.gaming.ce.server.service.SessionService;
import com.gaming.ce.server.util.CookieUtil;
import com.gaming.ce.server.util.DigestUtil;
import com.gaming.ce.server.util.TokenUtil;
import com.gaming.ce.user.design.entity.User;
import com.gaming.ce.user.repository.UserRepository;

@Service
@Transactional
public class AuthenticationServiceImpl implements Authenticatior,Authorization{
	
	private static final Log log = LogFactory.getLog(AuthenticationServiceImpl.class);

	@Autowired
	Environment env;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SessionService sessionService;
	
	@Override
	public int login(String userName, String password,
			HttpServletRequest request, HttpServletResponse response) {

		User user = userRepository.findUserByUserName(userName);

		//验证密码
		if (!DigestUtil.sha256_base64(password).equals(user.getPassword())) {
			return Code.ERROR_PASSWORD_OR_USERNAME_NOT_MATCH;
		}
		
		//退出以前登录的Session
		List<Session> sessions = sessionService.findSessionsByUserId(user.getId());
		for( Session session : sessions ){
			sessionService.logoutSessionByToken(session.getSessionSign());
		}
		
		//创建新的Session
		Session session = sessionService.createUserSession(userName, user.getId(), request , response );

		log.trace("User:"+userName+" login with ip:"+session.getSessionIp());

		return Code.SUCCESS;
	}
	
	@Override
	public int logout(HttpServletRequest request, HttpServletResponse response) {
		sessionService.logoutSessionByToken(CookieUtil.getCookie(request, TokenUtil.TOKEN_COOKIE_NMAE));
		return Code.SUCCESS;
	}

	@Override
	public boolean isUserAllowed(Long userId, Set<String> rolesSet) {
		
		Iterator<String> iterator = rolesSet.iterator();
		while( iterator.hasNext() ){
			
			String name = iterator.next();
			
			String rolesHas = userRepository.findRolesByUserId(userId);
			if(rolesHas.contains(name)){
				return true;
			}
		}
		
		return false;
	}

	@Override
	public int authorization(Long userId, Set<String> rolesSet) {
		
		User user = userRepository.findOne(userId);
		user.setRoles(rolesSet.toString());
		userRepository.save(user);
		
		return Code.SUCCESS;
	}
	
}

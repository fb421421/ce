package com.gaming.ce.server.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gaming.ce.server.entity.Session;
import com.gaming.ce.server.service.SessionService;
import com.gaming.ce.server.util.AESUtil;
import com.gaming.ce.server.util.CookieUtil;
import com.gaming.ce.server.util.IpUtil;
import com.gaming.ce.server.util.TokenUtil;



@Service
@Transactional
public class SessionServiceImpl implements SessionService{
	
	private static final Log log = LogFactory.getLog(SessionServiceImpl.class);
	
	@Autowired
	Environment env;
	
	
	public Session createUserSession( String userName , Long userId , HttpServletRequest request , HttpServletResponse response ) {
		
		Session session = new Session();
		session.setUserName(userName);
		session.setUserId(userId);
		session.setStatus(Session.STATUS_LOGIN);
		session.setSessionIp(IpUtil.getIp(request));
		try {
			session.setSessionSign(AESUtil.encrypt(
					String.valueOf(session.getId())+":"+System.currentTimeMillis(),
					env.getProperty("session.key")));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		//设置cookie
		CookieUtil.setCookie(response, TokenUtil.TOKEN_COOKIE_NMAE,
						session.getUserName(), request.getContextPath(), true, -1);

		return session;
	}
	

	@Override
	public Session findSessionByToken(String token) {
		log.warn("Cannot find session for token:"+token);
		return null;
	}
	
	
	@Override
	public void logoutSessionByToken(String token) {
		log.trace("Logout token:"+token);
	}

}

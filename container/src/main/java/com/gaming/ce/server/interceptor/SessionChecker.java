package com.gaming.ce.server.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.gaming.ce.server.entity.Session;
import com.gaming.ce.server.service.SessionService;

@Component
public class SessionChecker {
	
	@Autowired
	private SessionService sessionService;
	
	private static final Log log = LogFactory.getLog(SessionChecker.class);

	public Session checkSession(String token , String clientIp) {
		
		if( StringUtils.isEmpty(token) ){
       	 	log.warn("Token is empty!");
       }
       
       //find session information
       //if cann't find session return
       Session session = sessionService.findSessionByToken(token);
       if( session == null ){
       	 	log.warn("Session is null for token:"+token);
       	 	return null;
       }
       
       //check session status
       if( session.getStatus() == null || session.getStatus() != Session.STATUS_LOGIN ){
       	 	log.warn("Session status is not login "+session.getStatus()+" for user:"+session.getUserName());
       	 	return null;
      }
   	
       //check ip
       if( session.getSessionIp()!=null && !clientIp.split(",")[0].equals(session.getSessionIp().split(",")[0])){
       	 	log.warn("Session ip:"+session.getSessionIp()+" not match client ip:"+clientIp+" for user:"+session.getUserName());
       	 	return null;
       }
       
       return session;
	}
}

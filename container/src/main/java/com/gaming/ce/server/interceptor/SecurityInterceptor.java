package com.gaming.ce.server.interceptor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.gaming.ce.server.entity.Session;
import com.gaming.ce.server.service.Authorization;
import com.gaming.ce.server.util.CookieUtil;
import com.gaming.ce.server.util.IpUtil;
import com.gaming.ce.server.util.TokenUtil;



@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter{
	
	private static final Log log = LogFactory.getLog(SecurityInterceptor.class);
	
	@Autowired
	private SessionChecker sessionChecker;
	
	@Autowired
	private Authorization authorization;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		if (handler instanceof HandlerMethod) {
		    HandlerMethod method = (HandlerMethod) handler;
		    if (method.getMethod().isAnnotationPresent(RolesAllowed.class)) {
		       
		    	 //detect token
	            // if cann't find token return
	            String token = CookieUtil.getCookie(request, TokenUtil.TOKEN_COOKIE_NMAE);
	            Session session = sessionChecker.checkSession(token,IpUtil.getIp(request));
	            if( session == null ){
	            	 response.sendError(401);
	                 return false;
	            }
	           
	            //Verify user access
	            RolesAllowed rolesAnnotation = method.getMethodAnnotation(RolesAllowed.class);
	            Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
	            
	            if( !authorization.isUserAllowed(session.getUserId(), rolesSet)){
	            	 log.warn("User:"+session.getUserName()+" do not has in role:"+rolesSet);
	            	 response.sendError(403);
	                 return false;
	            }
	            
		    }
		}
		
		return super.preHandle(request, response, handler);
	}

	
}

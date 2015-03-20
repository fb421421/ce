package com.gaming.ce.server.interceptor;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.gaming.ce.server.entity.Session;

@Component
public class WebSocketSecurityShankhandler extends HttpSessionHandshakeInterceptor{
	
	@Autowired
	private SessionChecker sessionChecker;

	@Override
	public boolean beforeHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		
		System.out.println(request.getHeaders());
		System.out.println(attributes);
		Session session = sessionChecker.checkSession(request.getHeaders().get("Cookie").get(0).split(";")[1], request.getHeaders().get("Host").get(0));
		if( session == null ){
			return false;
		}
		return super.beforeHandshake(request, response, wsHandler, attributes);
	}
}

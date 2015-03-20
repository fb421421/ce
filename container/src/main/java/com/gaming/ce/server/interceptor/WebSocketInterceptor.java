package com.gaming.ce.server.interceptor;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.stereotype.Component;


@Component
public class WebSocketInterceptor extends ChannelInterceptorAdapter {

	@Override
	public boolean preReceive(MessageChannel channel) {
		System.out.println("preReceive"+channel);
		return super.preReceive(channel);
	}
	
	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		StompHeaderAccessor headerAccessor= StompHeaderAccessor.wrap(message);
		System.out.println(headerAccessor);
		return super.preSend(message, channel);
	}
}

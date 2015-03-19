package com.gaming.ce.user.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gaming.ce.server.entity.Session;
import com.gaming.ce.server.protocol.VideoGameDataPackage;
import com.gaming.ce.user.service.CustomerSocketApiService;


@Service
public class SocketApiServiceImpl implements CustomerSocketApiService{
	
	private static final Log log = LogFactory.getLog(SocketApiServiceImpl.class);
	
	
	@Override
	public VideoGameDataPackage handleCommand(Session session,VideoGameDataPackage msg) {
		switch( msg.getCommand()){
			case CustomerSocketApiService.COMMAND_LOGIN:
				break;
			default:
				log.error("Unknown command:-->"+msg);
				break;
		}
		return msg;
	}
	

}

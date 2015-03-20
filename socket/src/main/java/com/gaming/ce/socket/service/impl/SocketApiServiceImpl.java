package com.gaming.ce.socket.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaming.ce.common.Code;
import com.gaming.ce.finacial.api.usecase.BalanceService;
import com.gaming.ce.server.entity.Session;
import com.gaming.ce.server.protocol.VideoGameDataPackage;
import com.gaming.ce.socket.service.SocketApiService;


@Service
public class SocketApiServiceImpl implements SocketApiService{
	
	private static final Log log = LogFactory.getLog(SocketApiServiceImpl.class);
	
	@Autowired
	private BalanceService balanceService;
	
	@Override
	public VideoGameDataPackage handleCommand(Session session,VideoGameDataPackage msg) {
		switch( msg.getCommand()){
			case SocketApiService.COMMAND_LOGIN:
				break;
			case SocketApiService.COMMAND_FIND_BALANCE:
				findBalance(session,msg);
				break;
			default:
				log.error("Unknown command:-->"+msg);
				break;
		}
		return msg;
	}

	private VideoGameDataPackage findBalance( Session session , VideoGameDataPackage msg) {
		msg.getParameters().put(SocketApiService.PARM_CODE,Code.SUCCESS);
		msg.getParameters().put(SocketApiService.PARM_MSG, Code.getMessage(Code.SUCCESS));
		msg.getParameters().put(SocketApiService.PARM_BALANCE, balanceService.findBalance(session.getUserId()));
		return msg;
	}
	

}

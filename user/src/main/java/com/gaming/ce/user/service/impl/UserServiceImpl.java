package com.gaming.ce.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaming.ce.common.constant.Role;
import com.gaming.ce.server.service.Authorization;
import com.gaming.ce.server.service.SessionService;
import com.gaming.ce.server.util.DigestUtil;
import com.gaming.ce.user.entity.User;
import com.gaming.ce.user.repository.UserRepository;
import com.gaming.ce.user.service.UserService;


@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private Authorization authorization;
	

	@Override
	public boolean isUserExisted(String userName) {
		
		if(userRepository.findUserIdByUserName(userName)!=null){
			return true;
		}
		
		return false;
	}


	@Override
	public User createUserAndAuthorization(String userName, String password) {
		
		//创建会员
		User user = new User();
		user.setUserName(userName);
		user.setPassword(DigestUtil.sha256_base64(password));
		user.setRoles(Role.USER);
		userRepository.save(user);
		
		return user;
	}


}

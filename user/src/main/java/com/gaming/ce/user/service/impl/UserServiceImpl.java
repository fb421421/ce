package com.gaming.ce.user.service.impl;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaming.ce.common.Role;
import com.gaming.ce.server.entity.Session;
import com.gaming.ce.server.service.Authorization;
import com.gaming.ce.server.service.SessionService;
import com.gaming.ce.server.util.DigestUtil;
import com.gaming.ce.user.design.entity.User;
import com.gaming.ce.user.design.service.UserService;
import com.gaming.ce.user.repository.UserRepository;


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
	public User findCurrentUserByToken(String token) {
		return findCurrentyUser(token);
	}
	
	public User findCurrentyUser(String token) {

		Session session = sessionService.findSessionByToken(token);
		
		User user = userRepository.findOne(session.getUserId());

		return user;
	}

	@Override
	public User createUserAndAuthorization(String userName, String password) {
		
		//创建会员
		User user = new User();
		user.setUserName(userName);
		user.setPassword(DigestUtil.sha256_base64(password));
		userRepository.save(user);
		
		//授权
		authorization.authorization(user.getId(), new HashSet<>(Arrays.asList(Role.USER)));
		
		return user;
	}

	@Override
	public User findUserByUserName(String userName) {
		return userRepository.findUserByUserName(userName);
	}

}

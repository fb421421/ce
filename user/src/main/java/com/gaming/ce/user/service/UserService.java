package com.gaming.ce.user.service;

import com.gaming.ce.user.entity.User;



public interface UserService {
	
	boolean isUserExisted( String userName );
	
	User createUserAndAuthorization(String userName , String password);
	
}

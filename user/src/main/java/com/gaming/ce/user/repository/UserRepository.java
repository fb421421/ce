package com.gaming.ce.user.repository;

import com.gaming.ce.user.entity.User;



public interface UserRepository {
	
	Long findUserIdByUserName( String userName );
	
	User save(User user);

	String findPasswordByUserName(String userName);

	String findRolesByUserId(Long userId);

	void updateRoles(String roles , Long userId);
}

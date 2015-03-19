package com.gaming.ce.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gaming.ce.user.design.entity.User;



public interface UserRepository extends JpaRepository<User,Long>{
	
	public User findUserByUserName( String userName );
	
	@Query("SELECT model.roles FROM User model WHERE model.id=:userId")
	public String findRolesByUserId( @Param("userId") Long userId );
	
}

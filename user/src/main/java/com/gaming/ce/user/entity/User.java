package com.gaming.ce.user.entity;

import java.io.Serializable;
import java.util.Date;


public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7924386144273140685L;

	private Long id;
	
	private String userName;
	
	private String password;
	
	private String roles;
	
	private Date createTime = new Date(System.currentTimeMillis());
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	

}
package com.gaming.ce.user.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gaming.ce.user.entity.User;
import com.gaming.ce.user.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Long findUserIdByUserName(String userName) {
		List<Map<String, Object>> list = jdbcTemplate.queryForList(
				"SELECT id FROM User WHERE userName=? ", userName );
		if( list!=null && list.size()>0){
			return (Long) list.get(0).get("id");
		}else{
			return null;
		}
	}

	@Override
	public User save(User user) {

		String sql = "INSERT INTO User "
				+ "( userName, password , roles , createTime ) VALUES (?,?,?,?)";

		jdbcTemplate.update(sql,
				new Object[] { user.getUserName(),
						user.getPassword(),
						user.getRoles(),
						user.getCreateTime() });

		return null;
	}

	@Override
	public String findPasswordByUserName(String userName) {
		List<Map<String, Object>> list = jdbcTemplate.queryForList(
				"SELECT password FROM User WHERE userName=? ", userName );
		if( list!=null && list.size()>0){
			return (String) list.get(0).get("password");
		}else{
			return null;
		}
	}

	@Override
	public String findRolesByUserId(Long userId) {
		List<Map<String, Object>> list = jdbcTemplate.queryForList(
				"SELECT roles FROM User WHERE id=? ", userId );
		if( list!=null && list.size()>0){
			return (String) list.get(0).get("roles");
		}else{
			return null;
		}
	}

	@Override
	public void updateRoles(String roles , Long userId) {
		 jdbcTemplate.update("UPDATE User SET roles=? WHERE id=? ", roles , userId );
	}
}

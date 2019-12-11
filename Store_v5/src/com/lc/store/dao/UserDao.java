package com.lc.store.dao;

import java.sql.SQLException;

import com.lc.store.domain.User;

public interface UserDao {

	//实现用户注册功能
	public void regist(User user) throws Exception;

	public User userActive(String code) throws SQLException;

	public void update(User user) throws SQLException;

	public User userLogin(User user) throws SQLException;

}

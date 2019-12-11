package com.lc.store.service;

import java.sql.SQLException;

import com.lc.store.domain.User;

public interface UserService {

	//实现用户注册功能
	public void regist(User user) throws Exception;

	//用户激活
	public Boolean userActive(String code) throws SQLException;

	public User userLogin(User user) throws SQLException;

}

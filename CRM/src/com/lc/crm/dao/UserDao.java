package com.lc.crm.dao;

import java.util.List;

import com.lc.crm.domain.User;

/**
 * 用户模块dao层的接口
 * @author user LC
 *
 */
public interface UserDao {

	public void regist(User user);

	public List<User> login(User user);

	public List<User> findAll();

}

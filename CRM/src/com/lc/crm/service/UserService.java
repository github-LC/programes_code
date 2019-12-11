package com.lc.crm.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.lc.crm.domain.User;

/**
 * 用户模块的接口，定义用户模块要完成的功能
 * @author user LC
 *
 */
public interface UserService {

	public void regist(User user);

	public List<User> login(User user);

	public List<User> findAll();

}

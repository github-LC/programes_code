package com.lc.crm.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import com.lc.crm.dao.UserDao;
import com.lc.crm.domain.User;
import com.lc.crm.service.UserService;
import com.lc.crm.utils.MD5Utils;
/**
 * 用户模块功能的实现
 * @author user LC
 *
 */
@Transactional
public  class UserServiceImpl implements UserService {

	//注入dao层
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	/**
	 * 用户注册功能
	 */
	@Override
	public void regist(User user) {
		// TODO Auto-generated method stub
		//使用工具类对密码进行加密
		user.setUser_state("1");
		user.setUser_password(MD5Utils.string2MD5(user.getUser_password()));
		user.setUser_password(user.getUser_password());
		System.out.println(user);
		userDao.regist(user);
	}


	/**
	 * 完成用户登陆功能
	 */
	@Override
	public List<User> login(User user) {
		// TODO Auto-generated method stub
		//对用户密码进行加密
		user.setUser_password(MD5Utils.string2MD5(user.getUser_password()));
		List<User> loginUser = userDao.login(user);
		if(loginUser!=null) {
			return loginUser;
		}
			
		return null;
	}


	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		List<User> list = userDao.findAll();
		return list;
	}

}

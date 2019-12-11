package com.lc.store.service.impl;

import java.sql.SQLException;

import com.lc.store.dao.ProductDao;
import com.lc.store.dao.UserDao;
import com.lc.store.dao.impl.UserDaoImpl;
import com.lc.store.domain.User;
import com.lc.store.service.UserService;
import com.lc.store.utils.BeanFactory;

public class UserServiceImpl implements UserService {

	/**
	 * 实现用户注册功能，调用dao层的功能
	 * @throws Exception 
	 */
	@Override
	public void regist(User user) throws Exception {
		// TODO Auto-generated method stub
		UserDao dao = (UserDao) BeanFactory.creatObject("UserDao");
		dao.regist(user);
	}

	/**
	 * 实现用户激活，激活成功修改用户状态和清空激活码
	 * @throws SQLException 
	 */
	public Boolean userActive(String code) throws SQLException {
		// TODO Auto-generated method stub
		UserDao dao = (UserDao) BeanFactory.creatObject("UserDao");
		User user = dao.userActive(code);
		if(user==null) {
			return false;
		}else {
			user.setState(1);
			user.setCode(null);
			//更新用户信息
			dao.update(user);
			return true;
		}
	
	}

	/**
	 * 用户登录校验功能
	 * @throws SQLException 
	 */
	public User userLogin(User user) throws SQLException {
		UserDao dao = (UserDao) BeanFactory.creatObject("UserDao");
		User uu = dao.userLogin(user);
		if(uu==null) {
			throw new RuntimeException("用户不存在或密码不正确");
		}else if(uu.getState()==0){
			throw new RuntimeException("用户未激活");
		}
		return uu;
	}
}
 
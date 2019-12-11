package com.lc.crm.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.lc.crm.dao.UserDao;
import com.lc.crm.domain.User;
/**
 * 用户模块dao层的接口的实现
 * @author user LC
 *
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	/**
	 * 用户注册功能，将用户注册信息持久化到数据库
	 */
	@Override
	public void regist(User user) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(user);
	}

	/**
	 * 查询用户登陆信息
	 */
	@Override
	public List<User> login(User user) {
		// TODO Auto-generated method stub
		System.out.println("dao:"+user);
		List<User> loginUser =  (List<User>) this.getHibernateTemplate().find("from User where user_code=? and user_password=?",user.getUser_code(),user.getUser_password());
		return loginUser;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return (List<User>) this.getHibernateTemplate().find("from User");
	}

}

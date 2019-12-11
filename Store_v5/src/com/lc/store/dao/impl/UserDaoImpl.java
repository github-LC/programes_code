package com.lc.store.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.lc.store.dao.UserDao;
import com.lc.store.domain.User;
import com.lc.store.utils.JDBCUtils;

public class UserDaoImpl implements UserDao {

	/**
	 * 实现用户注册功能
	 * 连接数据库，将注册信息写入数据库
	 * @throws Exception 
	 */
	@Override
	public void regist(User user) throws Exception {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		
		//向数据库写入注册信息
		qr.update("insert into user values(?,?,?,?,?,?,?,?,?,?)",user.getUid(),
				user.getUsername(),user.getPassword(),user.getName(),
				user.getEmail(),user.getTelephone(),user.getBirthday(),
				user.getSex(),user.getState(),user.getCode());
	}

	/**
	 * 激活用户
	 * @throws SQLException 
	 */
	public User userActive(String code) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		 User user = qr.query("select * from user where code=?",new BeanHandler<User>(User.class),code);
		return user;
	}

	/**
	 * 更新用户信息
	 * @throws SQLException 
	 */
	@Override
	public void update(User user) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update user set username=?,password=?,name=?,email=?,telephone=?,birthday=?,sex=?,state=?,code=? where uid=?";
		Object[] params = {user.getUsername(),user.getPassword(),user.getName(),
				user.getEmail(),user.getTelephone(),user.getBirthday(),
				user.getSex(),user.getState(),user.getCode(),user.getUid()};
		qr.update(sql,params);
	}

	/**
	 * 校验用户是否存在
	 * @throws SQLException 
	 */
	@Override
	public User userLogin(User user) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from user where username=? and password=?";
		return qr.query(sql,new BeanHandler<User>(User.class),user.getUsername(),user.getPassword());
	}
}

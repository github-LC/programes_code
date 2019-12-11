package com.lc.store.dao.impl;


import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.lc.store.dao.CategoryDao;
import com.lc.store.domain.Category;
import com.lc.store.utils.JDBCUtils;

public class CategoryDaoImpl implements CategoryDao {
	
	/**
	 * 添加分类商品
	 */
	@Override
	public void addCategory(String cname, String cid) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into category values(?,?)";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,cid,cname);
	}

	/**
	 * 查询商品分类信息
	 * @throws Exception 
	 */
	@Override
	public List<Category> allCats() throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from category";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql,new BeanListHandler<Category>(Category.class));
	}

}

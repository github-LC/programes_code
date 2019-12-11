package com.lc.store.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.lc.store.dao.CartDao;
import com.lc.store.domain.Product;
import com.lc.store.utils.JDBCUtils;

/**
 * 购物车dao层功能实现
 * @author user LC
 *
 */
public class CartDaoImpl implements CartDao {

	/**
	 * 查询商品的详细信息
	 */
	@Override
	public Product queryProduct(String pid) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from product where pid=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql,new BeanHandler<Product>(Product.class),pid);
	}

}

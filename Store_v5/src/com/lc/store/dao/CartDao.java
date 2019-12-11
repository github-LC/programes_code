package com.lc.store.dao;

import com.lc.store.domain.Product;

/**
 * 购物车dao层功能实现接口
 * @author user LC
 *
 */
public interface CartDao {

	public Product queryProduct(String pid) throws Exception;

}

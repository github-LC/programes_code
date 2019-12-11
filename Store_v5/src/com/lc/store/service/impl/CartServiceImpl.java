package com.lc.store.service.impl;

import com.lc.store.dao.CartDao;
import com.lc.store.dao.impl.CartDaoImpl;
import com.lc.store.domain.Product;
import com.lc.store.service.CartService;
import com.lc.store.utils.BeanFactory;

/**
 * 购物车业务层功能实现
 * @author user LC
 *
 */
public class CartServiceImpl implements CartService {

	/**
	 * 根据商品的id查询商品信息
	 */
	@Override
	public Product queryProduct(String pid) throws Exception {
		// TODO Auto-generated method stub
		CartDao dao = (CartDao) BeanFactory.creatObject("CartDao");
		return dao.queryProduct(pid);
	}

}

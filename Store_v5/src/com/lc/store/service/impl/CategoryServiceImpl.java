package com.lc.store.service.impl;

import java.util.List;

import com.lc.store.dao.CategoryDao;
import com.lc.store.dao.impl.CategoryDaoImpl;
import com.lc.store.domain.Category;
import com.lc.store.service.CategoryService;
import com.lc.store.utils.BeanFactory;
import com.lc.store.utils.JedisUtils;

import redis.clients.jedis.Jedis;

public class CategoryServiceImpl implements CategoryService{

	/**
	 * 添加商品分类
	 */
	@Override
	public void addCategory(String cname, String cid) throws Exception {
		// TODO Auto-generated method stub
		CategoryDao dao = (CategoryDao) BeanFactory.creatObject("CategoryDao");
		dao.addCategory(cname,cid);
		//清空Redis中的缓存，并重新查询
		Jedis jedis = JedisUtils.getJedis();
		jedis.del("allCats");
		JedisUtils.closeJedis(jedis);
	}

	/**
	 * 调用dao层的功能实现商品分类的查询
	 * @throws Exception 
	 */
	@Override
	public List<Category> allCats() throws Exception {
		// TODO Auto-generated method stub
		CategoryDao dao =  (CategoryDao) BeanFactory.creatObject("CategoryDao");
		List<Category> list = dao.allCats();
		return list;
	}

}

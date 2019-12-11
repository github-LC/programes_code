package com.lc.store.dao;

import java.util.List;

import com.lc.store.domain.PageModel;
import com.lc.store.domain.Product;

/**
 * 商品信息查询
 * @author user LC
 *
 */
public interface ProductDao {

	List<Product> product_hots() throws Exception;

	List<Product> product_news() throws Exception;

	Product product_info(int id) throws Exception;

	List<Product> findCategoryProducts(String cid, PageModel pageModel) throws Exception;

	long queryNumber(String cid) throws Exception;

	int queryNumber() throws Exception;

	List queryAll(PageModel pageMoel) throws Exception;

	void addProduct(Product product) throws Exception;
}

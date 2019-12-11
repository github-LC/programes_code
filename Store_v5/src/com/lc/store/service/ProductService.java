package com.lc.store.service;

import java.util.List;

import com.lc.store.domain.PageModel;
import com.lc.store.domain.Product;

/**
 * 商品查询
 * @author user LC
 *
 */
public interface ProductService {

	List<Product> product_hots() throws Exception;

	List<Product> product_news() throws Exception;

	Product product_info(int pid) throws Exception;

	PageModel product_list(String cid, int currentPage) throws Exception;

	PageModel findProductsWithPage(int currentPage) throws Exception;

	void addProduct(Product product) throws Exception;

}

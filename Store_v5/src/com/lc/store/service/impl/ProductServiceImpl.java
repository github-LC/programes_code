package com.lc.store.service.impl;

import java.util.List;

import com.lc.store.dao.OrderDao;
import com.lc.store.dao.ProductDao;
import com.lc.store.dao.impl.ProductDaoImpl;
import com.lc.store.domain.PageModel;
import com.lc.store.domain.Product;
import com.lc.store.service.ProductService;
import com.lc.store.utils.BeanFactory;

public class ProductServiceImpl implements ProductService {

	/**
	 * 查询热门商品
	 */
	@Override
	public List<Product> product_hots() throws Exception {
		// TODO Auto-generated method stub
		ProductDao dao = (ProductDao) BeanFactory.creatObject("ProductDao");
		List<Product> list = dao.product_hots();
		return list;
	}

	/**
	 * 查询最新商品
	 */
	@Override
	public List<Product> product_news() throws Exception {
		// TODO Auto-generated method stub
		ProductDao dao = (ProductDao) BeanFactory.creatObject("ProductDao");
		List<Product> list = dao.product_news();
		return list;
	}

	/**
	 * 商品详情查询
	 */
	@Override
	public Product product_info(int pid) throws Exception {
		// TODO Auto-generated method stub
		ProductDao dao = (ProductDao) BeanFactory.creatObject("ProductDao");
		Product product = dao.product_info(pid);
		return product;
	}

	/**
	 * 分页查询所有商品
	 */
	@Override
	public PageModel product_list(String cid,int currentPage) throws Exception {
		// TODO Auto-generated method stub
		PageModel pageModel = new PageModel();
		
		pageModel.setProductType(cid);
		pageModel.setCurrentPage(currentPage);
		//设置每页查询的数量
		pageModel.setEveryPageNum(6);
		//查询该类别的所有商品
		ProductDao dao = (ProductDao)BeanFactory.creatObject("ProductDao");
		//查询该类别下的所有商品数目
		int number = (int)dao.queryNumber(cid);
		
		//将数据保存到PageModel中
		pageModel.setTotalNum(number);
//		//设置开始查询的索引
//		pageModel.setBeginPage((pageModel.getCurrentPage()-1)*pageModel.getEveryPageNum());
//		//设置总共的分页数
//		pageModel.setTotalPage(pageModel.getTotalNum()/pageModel.getEveryPageNum());
		//关联url
		pageModel.setUrl("ProductServlet?method=product_list");
		
		List<Product> list = dao.findCategoryProducts(cid,pageModel);
		pageModel.setList(list);
		
		return pageModel;
	}

	/**
	 * 后台分页查询所有的商品
	 */
	@Override
	public PageModel findProductsWithPage(int currentPage) throws Exception {
		// TODO Auto-generated method stub
		PageModel pageModel = new PageModel();
		pageModel.setCurrentPage(currentPage);
		pageModel.setEveryPageNum(5);
		
		//查询商品总数
		ProductDao dao = (ProductDao) BeanFactory.creatObject("ProductDao");
		int num = dao.queryNumber();
		pageModel.setTotalNum(num);
		
		//查询所有的商品
		List<Product> list = dao.queryAll(pageModel);
		pageModel.setList(list);
		
		//关联url
		pageModel.setUrl("AdminProductServlet?method=findProductsWithPage");
		return pageModel;
	}

	/**
	 * 添加商品
	 */
	@Override
	public void addProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		ProductDao dao = (ProductDao) BeanFactory.creatObject("ProductDao");
		dao.addProduct(product);
	}
	
	
}

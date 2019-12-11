package com.lc.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lc.store.domain.Product;
import com.lc.store.service.ProductService;
import com.lc.store.service.impl.ProductServiceImpl;
import com.lc.store.web.base.BaseServlet;

/**
 * 为进入首页做准备工作
 * @author user LC
 *
 */
public class IndexServlet extends BaseServlet{
	
	/**
	 * WebConent下的index.jsp页面跳转是没有method，所以执行默认的execute方法
	 * 实现首页热门商品和最新商品的查询
	 * 调用service和dao层的功能从数据库中查询商品信息并在首页上显示
	 * @throws Exception 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//调用业务层功能
		ProductService service = new ProductServiceImpl();
		
		//返回查询的商品集合
		List<Product> list01 = service.product_hots();
		List<Product> list02 = service.product_news();
		
		//将集合添加到request中
		request.setAttribute("list01",list01);
		request.setAttribute("list02",list02);
		return "/jsp/index.jsp";
	}
}


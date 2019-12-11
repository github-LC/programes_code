package com.lc.store.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lc.store.domain.PageModel;
import com.lc.store.domain.Product;
import com.lc.store.service.ProductService;
import com.lc.store.service.impl.ProductServiceImpl;
import com.lc.store.web.base.BaseServlet;

/**
 * 商品功能模块
 * @author user LC
 *
 */
@SuppressWarnings("serial")
public class ProductServlet extends BaseServlet {
	
	/**
	 * 商品详情页面查询
	 */
	public String product_info(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//获取商品id
		String id = request.getParameter("id");
		//将商品id变成int类型
		int pid = Integer.parseInt(id);
		ProductService service = new ProductServiceImpl();
		Product product = service.product_info(pid);
		
		//将list集合存在request中
		request.setAttribute("product",product);
		return "/jsp/product_info.jsp";
	}
	
	/**
	 * 商品分类查询并分页
	 * 
	 */
	public String product_list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//获取商品id
		String cid = request.getParameter("cid");
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		PageModel pageModel = new PageModel();
		//调用业务层功能
		ProductService service = new ProductServiceImpl();
		pageModel = service.product_list(cid,currentPage);
		
		//将list集合存在request中
		request.setAttribute("page",pageModel);
		return "/jsp/product_list.jsp";
	}
	
	
}

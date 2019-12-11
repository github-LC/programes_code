package com.lc.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lc.store.domain.Category;
import com.lc.store.service.CategoryService;
import com.lc.store.service.impl.CategoryServiceImpl;
import com.lc.store.utils.JedisUtils;
import com.lc.store.utils.UUIDUtils;
import com.lc.store.web.base.BaseServlet;

import redis.clients.jedis.Jedis;

/**
 * 后台管理系统类界面
 * @author user LC
 *
 */
public class AdminCategoryServlet extends BaseServlet {

	/**
	 * 跳转到分类界面
	 * @param request
	 * @param response
	 * @return
	 */
	public String addCategoryUI(HttpServletRequest request, HttpServletResponse response) {
		
		return "/admin/category/add.jsp";
		
	}
	/**
	 * 添加分类信息
	 * 将分类信息写入数据库
	 * 获取Redis对象
	 * 清空Redis中的缓存保证让页面响应到更新后的内容
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public String addCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String cname = request.getParameter("cname");
		String cid = UUIDUtils.getCode();
		
		CategoryService service = new CategoryServiceImpl();
		service.addCategory(cname,cid);
		
		response.sendRedirect("AdminCategoryServlet?method=getAllCategory");
		return null;
		
	}
	/**获取所有的分类信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public String getAllCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CategoryService service = new CategoryServiceImpl();
		List<Category> list = service.allCats();
		
		//将分类信息传递
		request.setAttribute("list", list);
		return "/admin/category/list.jsp";
		
	}
	
}

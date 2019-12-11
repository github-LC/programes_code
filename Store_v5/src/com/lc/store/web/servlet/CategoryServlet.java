package com.lc.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lc.store.domain.Category;
import com.lc.store.service.CategoryService;
import com.lc.store.service.impl.CategoryServiceImpl;
import com.lc.store.utils.JedisUtils;
import com.lc.store.web.base.BaseServlet;

import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

/**
 * 实现商品功能模块
 * @author user LC
 *
 */
public class CategoryServlet extends BaseServlet{
	
	/**
	 * 查询分类商品，并响应给客户端的ajax请求
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public String getAllCats(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//获取jedis
		Jedis jedis = JedisUtils.getJedis();
		
		String jsonStr = jedis.get("allCats");
		//如果Jedis中没有缓存
		if(jsonStr==null||"".equals(jsonStr)) {
			System.out.println("redis中没有缓存");
			//调用service层和doo层的功能
			CategoryService service = new CategoryServiceImpl();
			List<Category> list = service.allCats();
			
			//将list转换为json格式的数据并响应给客户端
			jsonStr = JSONArray.fromObject(list).toString();
			//将json保存在redis中
			jedis.set("allCats",jsonStr);
			//告诉浏览器本次响应的数据是json格式的字符串
			response.setContentType("application/json;charset=utf-8");
			//将json格式的数据响应出去
			response.getWriter().write(jsonStr);
		}else {
			System.out.println("redis中有缓存");
			//告诉浏览器本次响应的数据是json格式的字符串
			response.setContentType("application/json;charset=utf-8");
			//将json格式的数据响应出去
			response.getWriter().write(jsonStr);
		}
		
		
		return null;
	}
}

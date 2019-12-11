package com.lc.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lc.store.domain.Order;
import com.lc.store.service.OrderService;
import com.lc.store.service.impl.OrderServiceImpl;
import com.lc.store.web.base.BaseServlet;

import net.sf.json.JSONArray;

/**
 * 实现系统后台订单管理
 * @author user LC
 *
 */
public class AdminOrderServlet extends BaseServlet {

	/**
	 * 查询各种状态的订单
	 * 多个链接共享相同的代码段
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public String findOrders(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Order> list = null;
		String state = request.getParameter("state");
		OrderService service = new OrderServiceImpl();
		if("".equals(state)||state==null) {
			//查询全都订单
			list = service.findOrders();
		}else {
			int st = Integer.parseInt(state);
			list = service.findOrders(st);
		}
		
		request.setAttribute("order",list);
		return "admin/order/list.jsp";
	
	}
	
	
	public String findOrderWithId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String oid = request.getParameter("id");
		System.out.println(oid);
		OrderService service = new OrderServiceImpl();
		Order order = service.findEveryOrderById(oid);
		//转换成json格式的字符串并响应出去
		String jsonStr = JSONArray.fromObject(order.getList()).toString();
		System.out.println(jsonStr);
		//响应到客户端
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(jsonStr);
		return null;
	}
	

	/**
	 * 更新订单状态，并重定向到其他页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String updateOrderState(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String oid = request.getParameter("oid");
		
		//查询订单
		OrderService service = new OrderServiceImpl();
		Order order = service.findEveryOrderById(oid);
		order.setState(3);
		//更新订单状态
		service.updateOrder(order);
		
		//重定向到已发货界面
		response.sendRedirect("AdminOrderServlet?method=findOrders&state=3");
		return null;
	}
		
}

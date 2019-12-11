package com.lc.store.web.servlet;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lc.store.domain.Cart;
import com.lc.store.domain.CartItem;
import com.lc.store.domain.Order;
import com.lc.store.domain.OrderItem;
import com.lc.store.domain.PageModel;
import com.lc.store.domain.User;
import com.lc.store.service.OrderService;
import com.lc.store.service.impl.OrderServiceImpl;
import com.lc.store.utils.PaymentUtil;
import com.lc.store.utils.UUIDUtils;
import com.lc.store.web.base.BaseServlet;

/**
 * 订单模块功能的实现
 * @author user LC
 *
 */
public class OrderServlet extends BaseServlet {
	
	/**
	 * 保存订单功能
	 * 判断用户是否登陆，若没有登录进入提示页面提示用户登陆
	 * 若用户已经登陆，获取session中的购物车信息给订单和订单项赋值，一些值使用程序自动赋值
	 * 遍历获取购物车中的购物项给订单项赋值，将订单项添加到订单中
	 * 将订单传入service层和dao层
	 * 跳转到订单页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String saveOrder(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//判断用户是否登陆
		User user = (User) request.getSession().getAttribute("userInfo");
		//用户未登录
		if(null==user) {
			request.setAttribute("msg","用户还未登陆无法使用订单功能，请登录");
			return "jsp/info/jsp";
		}
		
		Order order = new Order();
		//用户已登陆
		//获取购物车中的信息
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		order.setOid(UUIDUtils.getCode());
		order.setOrdertime(new Date());
		order.setState(1);
		order.setTotal(cart.getTotal());
		order.setUser(user);
		
		
		//遍历购物项得到每个订单项
		for(CartItem cartItem : cart.getMap().values()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setItemid(UUIDUtils.getCode());
			orderItem.setTotal(cartItem.getSubTotal());
			orderItem.setQuantity(cartItem.getNumber());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			
			//将订单项添加到订单中
			order.getList().add(orderItem);
		}
		
		//将订单写入数据库中
		OrderService service = new OrderServiceImpl();
		service.saveOrder(order);
		
		//将订单保存在session中
		request.setAttribute("order",order);
		//清空购物车
		cart.clearCart();
		return "jsp/order_info.jsp";
		
		
	}
	
	/**
	 * 查询订单和订单下的每个订单项
	 *获取页面信息和用户id
	 *调用业务层和dao层功能返回pageModel
	 *将pageModel传递
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String findOrderById(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//获取当前页
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		//获取用户id
		User user = (User) request.getSession().getAttribute("userInfo");
		
		//将当前页向业务层传递
		OrderService service = new OrderServiceImpl();
		PageModel pageModel = service.findOrderById(currentPage,user);
		
		//将pageModel保存然后请求转发
		request.setAttribute("page",pageModel);
		return "jsp/order_list.jsp";
		
	}
	
	
	/**
	 * 查询每个订单下的订单项
	 */
	public String findEveryOrderById(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//获取订单id
		String oid = request.getParameter("oid");
		//将当前页向业务层传递
		OrderService service = new OrderServiceImpl();
		Order order = service.findEveryOrderById(oid);
		
		//将pageModel保存然后请求转发
		request.setAttribute("order",order);
		return "jsp/order_info.jsp";
		
	}
	
	/**
	 *支付功能
	 *获取客户端传递 的信息
	 *获取order对象，调用方法更新数据库中order对象
	 *完成对易付宝中的信息赋值，拼接并重定向到易付宝
	 *最后接收易付宝返回信息，更改数据库中订单的状态，并转发到提示页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String payOrder(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//获取信息
		String oid = request.getParameter("oid");
		String address = request.getParameter("address");
		String name = request.getParameter("name");
		String telephone = request.getParameter("telephone");
		String pd_FrpId = request.getParameter("pd_FrpId");
		
		//获取订单对象
		OrderService service = new OrderServiceImpl();
		Order order = service.findEveryOrderById(oid);
		
		//更新订单
		order.setAddress(address);
		order.setName(name);
		order.setTelephone(telephone);
		service.updateOrder(order);
		
		// 把付款所需要的参数准备好:
		String p0_Cmd = "Buy";
		//商户编号
		String p1_MerId = "10012006921";
		//订单编号
		String p2_Order = oid;
		//金额
		String p3_Amt = "0.01";
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		//接受响应参数的Servlet
		String p8_Url = "http://localhost:8080/Store_v5/OrderServlet?method=callBack";
		String p9_SAF = "";
		String pa_MP = "";
		String pr_NeedResponse = "1";
		//公司的秘钥
		String keyValue = "qV490l4XHJ6Dc32Zu7x90V43gVP4C5061938W01t47S1AY734Dcr27011546";
		
		//调用易宝的加密算法,对所有数据进行加密,返回电子签名
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
		
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		
		System.out.println(sb.toString());
		// 使用重定向：
		response.sendRedirect(sb.toString());
		
		return null;
	}
	
	/**
	 * 接受易付宝返回的信息，支付成功就更新数据库中的订单状态，并跳转到提示页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String callBack(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String p1_MerId = request.getParameter("p1_MerId");
		String r0_Cmd = request.getParameter("r0_Cmd");
		String r1_Code = request.getParameter("r1_Code");
		String r2_TrxId = request.getParameter("r2_TrxId");
		String r3_Amt = request.getParameter("r3_Amt");
		String r4_Cur = request.getParameter("r4_Cur");
		String r5_Pid = request.getParameter("r5_Pid");
		String r6_Order = request.getParameter("r6_Order");
		String r7_Uid = request.getParameter("r7_Uid");
		String r8_MP = request.getParameter("r8_MP");
		String r9_BType = request.getParameter("r9_BType");
		String rb_BankId = request.getParameter("rb_BankId");
		String ro_BankOrderId = request.getParameter("ro_BankOrderId");
		String rp_PayDate = request.getParameter("rp_PayDate");
		String rq_CardNo = request.getParameter("rq_CardNo");
		String ru_Trxtime = request.getParameter("ru_Trxtime");

		// hmac
		String hmac = request.getParameter("hmac");
		// 利用本地密钥和加密算法 加密数据
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
				r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
				r8_MP, r9_BType, keyValue);
		if (isValid) {
			// 有效
			if (r9_BType.equals("1")) {
				//更新订单状态
				//获取订单对象
				OrderService service = new OrderServiceImpl();
				Order order = service.findEveryOrderById(r6_Order);
				
				//更新订单
				order.setState(2);
				service.updateOrder(order);
				// 浏览器重定向
				response.setContentType("text/html;charset=utf-8");
				request.setAttribute("msg","支付成功！订单号：" + r6_Order + "金额：" + r3_Amt);
			} else if (r9_BType.equals("2")) {
				// 修改订单状态:
				// 服务器点对点，来自于易宝的通知
				System.out.println("收到易宝通知，修改订单状态！");//
				// 回复给易宝success，如果不回复，易宝会一直通知
				response.getWriter().print("success");
			}

		} else {
			throw new RuntimeException("数据被篡改！");
		}
		
		return "jsp/info.jsp";
	}
}

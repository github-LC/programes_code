package com.lc.store.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lc.store.domain.Cart;
import com.lc.store.domain.CartItem;
import com.lc.store.domain.Product;
import com.lc.store.service.CartService;
import com.lc.store.service.impl.CartServiceImpl;
import com.lc.store.web.base.BaseServlet;
/**
 * 实现购物车相关的功能模块
 * @author user LC
 *
 */
public class CartServlet extends BaseServlet {

	public String addCartItemToCart(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//将购物车添加到session中，添加前先判断session中有没有购物车
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if(cart==null) {
			//获取购物车将购物车添加到session中
			cart = new Cart();
			request.getSession().setAttribute("cart",cart);
		}
		//获取商品id和数量
		String pid = request.getParameter("pid");
		int num = Integer.parseInt(request.getParameter("quantity"));
		
		//根据商品id获得商品信息
		CartService service = new CartServiceImpl();
		Product product = service.queryProduct(pid);
		
		//将商品信息和数量添加到购物项中
		CartItem cartItem = new CartItem();
		cartItem.setNumber(num);
		cartItem.setProduct(product);
		
		//将购物项添加到购物车中
		cart.addCart(cartItem);
		
		/*
		 * //将购物车添加到session中 request.getSession().setAttribute("cart",cart);
		 */
		return "jsp/cart.jsp";
		
	}
	
	/**
	 * 清空购物车
	 * @param request
	 * @param response
	 */
	public String clearCart(HttpServletRequest request, HttpServletResponse response) {
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		cart.clearCart();
		return "jsp/cart.jsp";
	}
	
	/**
	 * 移除购物项
	 * @param request
	 * @param response
	 */
	public String removeCartItem(HttpServletRequest request, HttpServletResponse response) {
		//从session中获取要删除的购物项
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		String pid = request.getParameter("pid");
		cart.removeCartItem(pid);
		return "jsp/cart.jsp";
	}
	
	
	/**
	 * 跳转到购物车页面
	 * @param request
	 * @param response
	 * @return
	 */
	public String jumpToCart(HttpServletRequest request, HttpServletResponse response) {
		
		return "jsp/cart.jsp";
	}
}

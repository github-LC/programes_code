package com.lc.store.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 购物车包含：
 * 购物项
 * 积分
 * 商品金额
 * @author user LC
 *
 */
public class Cart {
	
	private Map<String,CartItem> map = new HashMap<String,CartItem>();//个数不确实的购物项
	private double total;//总价和积分
	
	//添加购物项到购物车
	public void addCart(CartItem cartItem) {
		//先判断购物车中是否已经有该购物项要是有就用以前的数量加上现在的数量，要是没有直接添加
		String pid=cartItem.getProduct().getPid();
		
		//将当前的购物项加入购物车之前,判断之前是否买过这类商品
		//如果没有买过    list.add(cartItem);
		//如果买过: 获取到原先的数量,获取到本次的数量,相加之后设置到原先购物项上
		if(map.containsKey(pid)){
			//获取到原先的购物项
			CartItem oldItem=map.get(pid);
			oldItem.setNumber(oldItem.getNumber()+cartItem.getNumber());
			
		}else{
			map.put(pid, cartItem);
		}
	}
	
	//获取购物车中购物项，在cart.jsp页面中不能直接遍历map集合得到cartItem，所以用这个方法取出map中的购物项
	public Collection<CartItem> getCartItem() {
		return map.values();
	}
	//删除购物项
	public void removeCartItem(String pid) {
		map.remove(pid);
	}
	
	//清除购物车
	public void clearCart() {
		map.clear();
	}

	
	public Map<String, CartItem> getMap() {
		return map;
	}

	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}

	public double getTotal() {
		double sum = 0.0;
		//计算总价：将每一个购物项的小计取出来求和即可
		Collection<CartItem> values = map.values();
		for(CartItem cartItem : values) {
			sum = sum+cartItem.getSubTotal();
		}
		return sum;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Cart [map=" + map + ", total=" + total + "]";
	}

	public Cart(Map<String, CartItem> map, double total) {
		super();
		this.map = map;
		this.total = total;
	}

	public Cart() {
		
	}
	
	
	
}

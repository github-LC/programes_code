package com.lc.store.service;

import java.util.List;

import com.lc.store.domain.Order;
import com.lc.store.domain.OrderItem;
import com.lc.store.domain.PageModel;
import com.lc.store.domain.User;

/**
 * 订单模块功能实现的接口
 * @author user LC
 *
 */
public interface OrderService {

	public void saveOrder(Order order) throws Exception;

	public PageModel findOrderById(int currentPage, User user) throws Exception;

	public Order findEveryOrderById(String oid) throws Exception;

	public void updateOrder(Order order) throws Exception;

	public List<Order> findOrders() throws Exception;

	public List<Order> findOrders(int st) throws Exception;

}

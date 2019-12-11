package com.lc.store.dao;

import java.sql.Connection;
import java.util.List;

import com.lc.store.domain.Order;
import com.lc.store.domain.OrderItem;
import com.lc.store.domain.PageModel;
import com.lc.store.domain.User;

/**
 * 订单模块dao层的接口
 * @author user LC
 *
 */
public interface OrderDao {

	public void saveOrder(Connection connection, Order order) throws Exception;

	public void saveOrderItem(Connection connection, OrderItem orderItem) throws Exception;

	public int queryOrderNumber(User user) throws Exception;

	public List findOrderById(User user, PageModel pageModel) throws Exception;

	public Order findEveryOrderById(String oid) throws Exception;

	public void updateOrder(Order order) throws Exception;

	public List<Order> findOrders() throws Exception;

	public List<Order> findOrders(int st) throws Exception;

}

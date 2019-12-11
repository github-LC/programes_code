package com.lc.store.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.lc.store.dao.OrderDao;
import com.lc.store.domain.Order;
import com.lc.store.domain.OrderItem;
import com.lc.store.domain.PageModel;
import com.lc.store.domain.User;
import com.lc.store.service.OrderService;
import com.lc.store.utils.BeanFactory;
import com.lc.store.utils.JDBCUtils;

/**
 * 订单功能模块功能接口的实现
 * @author user LC
 *
 */
public class OrderServiceImpl implements OrderService {

	/**
	 * 查询所有的订单
	 */
	@Override
	public List<Order> findOrders() throws Exception {
		// TODO Auto-generated method stub
		OrderDao dao = (OrderDao) BeanFactory.creatObject("OrderDao");
		List<Order> list = dao.findOrders();
		return list;
	}

	/**
	 * 查询其他状态的订单
	 * @throws Exception 
	 */
	@Override
	public List<Order> findOrders(int st) throws Exception {
		// TODO Auto-generated method stub
		OrderDao dao = (OrderDao) BeanFactory.creatObject("OrderDao");
		List<Order> list = dao.findOrders(st);
		return list;
	}

	//查询每个订单的详情
	@Override
	public Order findEveryOrderById(String oid) throws Exception {
		// TODO Auto-generated method stub
		OrderDao dao = (OrderDao) BeanFactory.creatObject("OrderDao");
		Order order = dao.findEveryOrderById(oid);
		return order;
	}

	/**
	 * 查询订单和订单项
	 */
	@Override
	public PageModel findOrderById(int currentPage,User user) throws Exception {
		// TODO Auto-generated method stub
		PageModel pageModel = new PageModel();
		pageModel.setCurrentPage(currentPage);
		pageModel.setEveryPageNum(5);
		pageModel.setBeginPage(0);
		
		OrderDao dao = (OrderDao) BeanFactory.creatObject("OrderDao");
		//查询订单数量
		int num = dao.queryOrderNumber(user);
		pageModel.setTotalNum(num);
//		//设置总页数
//		pageModel.setTotalPage(pageModel.getTotalNum()/pageModel.getEveryPageNum());
		//设置查询的其实索引每个页面都能拿到分页数据查询必须要关联url
		pageModel.setUrl("OrderServlet?method=findOrderById");
//		pageModel.setBeginPage((pageModel.getCurrentPage()-1)*pageModel.getEveryPageNum());
		//关联URL要想
		//将pageModel传入dao层进行分页查询
		List list = dao.findOrderById(user,pageModel);
		pageModel.setList(list);
		return pageModel;
	}

	/**
	 * 将订单信息写入数据库
	 * @throws Exception 
	 */
	@Override
	public void saveOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
		
		//使用事务提交
		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			connection.setAutoCommit(false);
			OrderDao dao = (OrderDao) BeanFactory.creatObject("OrderDao");
			dao.saveOrder(connection,order);
			//遍历取出订单项并写入数据库中
			for(OrderItem orderItem : order.getList()) {
				dao.saveOrderItem(connection,orderItem);
			}
			
			//提交
			connection.commit();
		} catch (SQLException e) {
			
			//若发生错误就回滚
			connection.rollback();
		}finally {
			
			//释放资源
			connection.close();
		}
		
	}

	/**
	 * 更新订单数据
	 */
	@Override
	public void updateOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
		OrderDao dao = (OrderDao) BeanFactory.creatObject("OrderDao");
		dao.updateOrder(order);
	}

}

package com.lc.store.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.lc.store.dao.OrderDao;
import com.lc.store.domain.Order;
import com.lc.store.domain.OrderItem;
import com.lc.store.domain.PageModel;
import com.lc.store.domain.Product;
import com.lc.store.domain.User;
import com.lc.store.utils.JDBCUtils;

/**
 * 订单模块dao层的功能实现接口
 * @author user LC
 *
 */
public class OrderDaoImpl implements OrderDao {

	/**
	 * 查询其他状态的订单
	 */
	@Override
	public List<Order> findOrders(int st) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from orders where state=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql,new BeanListHandler<Order>(Order.class),st);
	}

	/**
	 * 查询所有的订单
	 */
	@Override
	public List<Order> findOrders() throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from orders";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql,new BeanListHandler<Order>(Order.class));
		
	}

	/**
	 * 更新数据库中的订单数据
	 */
	@Override
	public void updateOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
		String sql = "update orders set ordertime=?,total=?,state=?,address=?,name=?,telephone=? where oid=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,order.getOrdertime(),order.getTotal(),order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getOid());
	}

	/**
	 * 将订单信息写入数据库
	 */
	@Override
	public void saveOrder(Connection connection,Order order) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		qr.update(connection,sql,order.getOid(),order.getOrdertime(),order.getTotal(),order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getUser().getUid());
	}

	/**
	 * 将订单项写入数据库
	 */
	@Override
	public void saveOrderItem(Connection connection,OrderItem orderItem) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into orderitem values(?,?,?,?,?)";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		qr.update(connection,sql,orderItem.getItemid(),orderItem.getQuantity(),orderItem.getTotal(),orderItem.getProduct().getPid(),orderItem.getOrder().getOid());
	}

	/**
	 * 根据用户id查询订单数量
	 */
	@Override
	public int queryOrderNumber(User user) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select count(*) from orders where uid=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Long num = (Long) qr.query(sql,new ScalarHandler(),user.getUid());
		return num.intValue();
	}

	/**
	 * 根据用户id进行多表分页查询订单和订单项
	 */
	@Override
	public List findOrderById(User user, PageModel pageModel) throws Exception {
		// TODO Auto-generated method stub
		//查询用户订单
		String sql01 = "select * from orders where uid=? limit ?,?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		List<Order> list01 = qr.query(sql01,new BeanListHandler<Order>(Order.class),user.getUid(),pageModel.getCurrentPage(),pageModel.getEveryPageNum());
		
		//遍历取出每一个订单
		for(Order order : list01) {
			
			//查询每一个订单下的订单项和商品信息
			String sql02 = "select * from orderitem as o,product as p where o.pid=p.pid and oid=?";
			List<Map<String, Object>> list02 = qr.query(sql02,new MapListHandler(),order.getOid());
			
			//遍历每个list为每个订单项和商品项填充数据
			for(Map<String, Object> map : list02) {
				OrderItem orderItem = new OrderItem();
				Product product = new Product();
				
				//创建时间类型转换器
				DateConverter dc = new DateConverter();
				dc.setPattern("yyyy-MM-dd");
				ConvertUtils.register(dc,java.util.Date.class);
				//用BeanUtils自动将map中的数据填充到product和orderItem中
				BeanUtils.populate(orderItem,map);
				BeanUtils.populate(product,map);
				
				//将商品添加到订单项中订单项添加到订单中
				orderItem.setProduct(product);
				order.getList().add(orderItem);
				
			}
		}
		return list01;
	}

	/**
	 * 获取每个订单下的所有订单项详情
	 */
	@Override
	public Order findEveryOrderById(String oid) throws Exception {
		// TODO Auto-generated method stub
		String sql01 = "select * from orders where oid=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Order order = qr.query(sql01,new BeanHandler<Order>(Order.class),oid);
		
		//查询每一个订单下的订单项和商品信息
		String sql02 = "select * from orderitem as o,product as p where o.pid=p.pid and oid=?";
		List<Map<String, Object>> list = qr.query(sql02,new MapListHandler(),order.getOid());
			
		//遍历每个list为每个订单项和商品项填充数据
		for(Map<String, Object> map : list) {
			OrderItem orderItem = new OrderItem();
			Product product = new Product();
				
			//创建时间类型转换器
			DateConverter dc = new DateConverter();
			dc.setPattern("yyyy-MM-dd");
			ConvertUtils.register(dc,java.util.Date.class);
			//用BeanUtils自动将map中的数据填充到product和orderItem中
			BeanUtils.populate(orderItem,map);
			BeanUtils.populate(product,map);
				
			//将商品添加到订单项中订单项添加到订单中
			orderItem.setProduct(product);
			order.getList().add(orderItem);
		}
		return order;
	}

}

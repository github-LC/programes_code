package com.lc.store.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 抽取订单模块
 * @author user LC
 *
 */
public class Order {

	private String oid;//订单id
	private Date ordertime;//订单生成时间
	private double total;//交易金额,总价
	private int state;//订单状态
	private String address;//收货状态
	private String name;//收货人姓名
	private String telephone;//收货人电话
	private User user = new User();//用户id
	private List<OrderItem> list = new ArrayList<OrderItem>();//用于保存订单项的集合Z
	
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<OrderItem> getList() {
		return list;
	}
	public void setList(List<OrderItem> list) {
		this.list = list;
	}
	
	public Order(String oid, Date ordertime, double total, int state, String address, String name, String telephone,
			User user, List<OrderItem> list) {
		super();
		this.oid = oid;
		this.ordertime = ordertime;
		this.total = total;
		this.state = state;
		this.address = address;
		this.name = name;
		this.telephone = telephone;
		this.user = user;
		this.list = list;
	}
	
	
	public Order() {
		
	}
	
	
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", ordertime=" + ordertime + ", total=" + total + ", state=" + state + ", address="
				+ address + ", name=" + name + ", telephone=" + telephone + ", user=" + user + ", list=" + list + "]";
	}
	
	
	
}

package com.lc.store.domain;

/**
 * 抽取订单项模块
 * @author user LC
 *
 */
public class OrderItem {
	
	private String itemid;
	private Product product = new Product();//这里本来是用户的id,但是对象只能和对象发生关系不能和对象的属性发生关系
	private int quantity;//商品数量
	private double total;//小计
	private Order order = new Order();//订单编号，这里也用order对象表示对象与对象之间的关系
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	
	
	public OrderItem() {
		
	}
	
	
	public OrderItem(String itemid, Product product, int quantity, double total, Order order) {
		super();
		this.itemid = itemid;
		this.product = product;
		this.quantity = quantity;
		this.total = total;
		this.order = order;
	}
	
	
	@Override
	public String toString() {
		return "OrderItem [itemid=" + itemid + ", product=" + product + ", quantity=" + quantity + ", total=" + total
				+ ", order=" + order + "]";
	}
	
	
	
	
	
	
}

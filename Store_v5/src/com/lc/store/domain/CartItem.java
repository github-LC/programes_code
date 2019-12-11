package com.lc.store.domain;

/**
 * 购物项
 * 商品
 * 商品数量
 * 总价
 * 
 * @author user LC
 *
 */
public class CartItem {
	
	private Product product;//商品
	private int number;//数量
	private double subTotal;//总价
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getSubTotal() {
		return number*product.getShop_price();
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	
	
	public CartItem(Product product, int number, double subTotal) {
		super();
		this.product = product;
		this.number = number;
		this.subTotal = subTotal;
	}
	
	
	public CartItem() {
		
	}
	@Override
	public String toString() {
		return "CartItem [product=" + product + ", number=" + number + ", subTotal=" + subTotal + "]";
	}
	
}

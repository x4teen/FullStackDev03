package com.sportyshoe.bean;

public class CartItem {
	
	private int productId;
	private String productName;
	private double rate;
	private int qty;
	private double price;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public CartItem(int productId, String productName, double rate, int qty, double price) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.rate = rate;
		this.qty = qty;
		this.price = price;
	}
	
	
	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CartItem [productId=" + productId + ", productName=" + productName + ", rate=" + rate + ", qty=" + qty
				+ ", price=" + price + "]";
	}
	
	
	

}

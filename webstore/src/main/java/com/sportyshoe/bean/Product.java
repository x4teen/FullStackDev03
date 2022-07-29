package com.sportyshoe.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	private int id;
	private String productName;
	private String productBrand;
	private String productCategory;
	private double productSize;
	private boolean productGender;
	private String productDescription;
	private double listPrice;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getProductManufacturer() {
		return productBrand;
	}

	public void setProductManufacturer(String productManufacturer) {
		this.productBrand = productManufacturer;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public double getProductSize() {
		return productSize;
	}

	public void setProductSize(double productSize) {
		this.productSize = productSize;
	}

	public boolean isProductGender() {
		return productGender;
	}

	public void setProductGender(boolean productGender) {
		this.productGender = productGender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public double getListPrice() {
		return listPrice;
	}

	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", productManufacturer=" + productBrand
				+ ", productDescription=" + productDescription + ", productCategory=" + productCategory
				+ ", productSize=" + productSize + ", productGender=" + productGender + ", listPrice=" + listPrice
				+ "]";
	}
	
	

}

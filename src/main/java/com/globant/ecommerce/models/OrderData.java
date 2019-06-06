package com.globant.ecommerce.models;

import java.util.List;

public class OrderData {
	
	private List<OrderModel> orders;
	private List<ProductModel> products;
	
	public OrderData() {
		// TODO Auto-generated constructor stub
	}

	public OrderData(List<OrderModel> orders, List<ProductModel> products) {
		super();
		this.orders = orders;
		this.products = products;
	}

	public List<OrderModel> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderModel> orders) {
		this.orders = orders;
	}

	public List<ProductModel> getProducts() {
		return products;
	}

	public void setProducts(List<ProductModel> products) {
		this.products = products;
	}
	

}

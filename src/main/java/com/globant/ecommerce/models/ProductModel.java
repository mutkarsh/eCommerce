package com.globant.ecommerce.models;

import javax.persistence.Entity;

/**
 * 
 * @author utkarsh.mandade
 *
 */
@Entity
public class ProductModel {

	private int productid;
	private int quantity;
	private double price;

	public ProductModel() {
	}

	public ProductModel(int productid, int quantity, double price) {
		super();
		this.productid = productid;
		this.quantity = quantity;
		this.price = price;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}

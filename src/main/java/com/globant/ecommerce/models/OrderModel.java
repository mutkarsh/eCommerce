package com.globant.ecommerce.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @author utkarsh.mandade
 *
 */
@Entity
public class OrderModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderid;
	private int userid;
	private List<ProductModel> products;
	private double totalamount;
	private String address;
	private String deliverystatus;
	private String paymentstatus;
	private String expecteddelivery;
	private String transactionid;

	public OrderModel() {
	}

	public OrderModel(int orderid, int userid, List<ProductModel> products, double totalamount, String address,
			String deliverystatus, String paymentstatus, String expecteddelivery, String transactionid) {
		super();
		this.orderid = orderid;
		this.userid = userid;
		this.products = products;
		this.totalamount = totalamount;
		this.address = address;
		this.deliverystatus = deliverystatus;
		this.paymentstatus = paymentstatus;
		this.expecteddelivery = expecteddelivery;
		this.transactionid = transactionid;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public List<ProductModel> getProducts() {
		return products;
	}

	public void setProducts(List<ProductModel> products) {
		this.products = products;
	}

	public double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDeliverystatus() {
		return deliverystatus;
	}

	public void setDeliverystatus(String deliverystatus) {
		this.deliverystatus = deliverystatus;
	}

	public String getPaymentstatus() {
		return paymentstatus;
	}

	public void setPaymentstatus(String paymentstatus) {
		this.paymentstatus = paymentstatus;
	}

	public String getExpecteddelivery() {
		return expecteddelivery;
	}

	public void setExpecteddelivery(String expecteddelivery) {
		this.expecteddelivery = expecteddelivery;
	}

	public String getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

}

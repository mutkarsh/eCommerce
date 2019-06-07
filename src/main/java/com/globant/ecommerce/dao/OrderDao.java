package com.globant.ecommerce.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.globant.ecommerce.models.OrderModel;


public interface OrderDao {
	
	public List<OrderModel> viewOrder(int userid);
	public boolean addOrder(OrderModel order);
	public List<OrderModel> viewOrderByOrderId(int orderid);
	
	public int getOrderid(String transactionid);
//	public void updateOrder(OrderModel order);
	public boolean cancelorder(int orderid);
	
}


package com.globant.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.globant.ecommerce.models.OrderModel;

public interface OrderService {
	
	public List<OrderModel> viewOrder(int userid);
	public boolean addOrder(OrderModel order);
	public List<OrderModel> viewOrderByOrderId(int orderid);
	public int getOrderid(String transactionid);
	
//	public void updateOrder(OrderModel order);
	public boolean cancelorder(int orderid);
	

}

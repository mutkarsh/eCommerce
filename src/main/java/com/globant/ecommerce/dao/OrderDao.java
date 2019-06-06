package com.globant.ecommerce.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.globant.ecommerce.models.OrderModel;


public interface OrderDao {
	
	public List<OrderModel> viewOrder(int userid);
//	public void addOrder(OrderModel order);
//	public void updateOrder(OrderModel order);
//	public void cancelorder(int orderid);
	
}


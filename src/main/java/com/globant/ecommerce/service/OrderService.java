package com.globant.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.globant.ecommerce.models.OrderModel;

public interface OrderService {
	
	public List<OrderModel> viewOrder(int userid);
//	public void addOrder(OrderModel order);
//	public void updateOrder(OrderModel order);
//	public void cancelorder(int orderid);
	

}

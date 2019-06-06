package com.globant.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.ecommerce.dao.OrderDaoImpl;
import com.globant.ecommerce.models.OrderModel;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderDaoImpl orderdao;
	
	@Override
	public List<OrderModel> viewOrder(int userid) {
		// TODO Auto-generated method stub
		return orderdao.viewOrder(userid);
	}

//	@Override
//	public void addOrder(OrderModel order) {
//		// TODO Auto-generated method stub
//		orderdao.addOrder(order);
//		
//	}
//
//	@Override
//	public void updateOrder(OrderModel order) {
//		// TODO Auto-generated method stub
//		orderdao.updateOrder(order);
//		
//	}
//
//	@Override
//	public void cancelorder(int orderid) {
//		// TODO Auto-generated method stub
//		orderdao.cancelorder(orderid);
//	}

	
}

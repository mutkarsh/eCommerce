package com.globant.ecommerce.service;

import java.util.Calendar;
import java.util.Date;
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

	@Override
	public boolean addOrder(OrderModel order) {
		// TODO Auto-generated method stub
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 5);
		Date newDate = cal.getTime();
		
		order.setDeliverystatus("Not Delivered");
		order.setExpecteddelivery(newDate+"");
		return orderdao.addOrder(order);	
	}
	
	@Override
	public List<OrderModel> viewOrderByOrderId(int orderid) {
		// TODO Auto-generated method stub
		return orderdao.viewOrderByOrderId(orderid);
	}

	@Override
	public int getOrderid(String transactionid) {
		// TODO Auto-generated method stub
		return orderdao.getOrderid(transactionid);
	}
	
//
//	@Override
//	public void updateOrder(OrderModel order) {
//		// TODO Auto-generated method stub
//		orderdao.updateOrder(order);
//		
//	}
//
	@Override
	public boolean cancelorder(int orderid) {
		// TODO Auto-generated method stub
		return orderdao.cancelorder(orderid);
	}

	

	
}

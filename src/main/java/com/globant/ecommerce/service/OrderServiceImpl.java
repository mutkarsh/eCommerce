package com.globant.ecommerce.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.ecommerce.dao.OrderDaoImpl;
import com.globant.ecommerce.models.OrderModel;

/**
 * 
 * @author utkarsh.mandade
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDaoImpl orderdao;

	/**
	 * Fetch All orders  of specific user 
	 */
	@Override
	public List<OrderModel> viewOrder(int userid) {
		// TODO Auto-generated method stub
		List<OrderModel> orders = orderdao.viewOrder(userid);
		return orders;
	}

	/**
	 * Add a new order made
	 */
	@Override
	public boolean addOrder(OrderModel order) {
		// TODO Auto-generated method stub

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 5);
		Date newDate = cal.getTime();

		order.setDeliverystatus("Not Delivered");
		order.setExpecteddelivery(newDate + "");
		return orderdao.addOrder(order);
	}

	/**
	 * method to retrive a order by orderid
	 */
	@Override
	public List<OrderModel> viewOrderByOrderId(int orderid) {
		// TODO Auto-generated method stub
		return orderdao.viewOrderByOrderId(orderid);
	}

	/**
	 * Method to get orderid by transactionid
	 */
	@Override
	public int getOrderid(String transactionid) {
		// TODO Auto-generated method stub
		return orderdao.getOrderid(transactionid);
	}

	/**
	 * Method to cancel the order 
	 */
	@Override
	public boolean cancelorder(int orderid) {
		// TODO Auto-generated method stub
		return orderdao.cancelorder(orderid);
	}

	/**
	 * method to update the status of the order
	 */
	@Override
	public boolean updateorder(OrderModel order) {
		// TODO Auto-generated method stub
		return orderdao.updateorder(order);
	}

}

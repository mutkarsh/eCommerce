package com.globant.ecommerce.facade;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.ecommerce.dao.OrderDaoImpl;
import com.globant.ecommerce.models.OrderModel;
import com.globant.ecommerce.service.OrderServiceImpl;

/**
 * 
 * @author utkarsh.mandade
 *
 */
@Service
public class OrderFacadeImpl implements OrderFacade {

	@Autowired
	OrderServiceImpl orderservice;

	/**
	 * Fetch All orders  of specific user 
	 */
	@Override
	public List<OrderModel> viewOrder(int userid) {
		// TODO Auto-generated method stub
		List<OrderModel> orders = orderservice.viewOrder(userid);
		System.out.println(orders.size());
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
		return orderservice.addOrder(order);
	}

	/**
	 * method to retrive a order by orderid
	 */
	@Override
	public List<OrderModel> viewOrderByOrderId(int orderid) {
		// TODO Auto-generated method stub
		return orderservice.viewOrderByOrderId(orderid);
	}

	/**
	 * Method to get orderid by transactionid
	 */
	@Override
	public int getOrderid(String transactionid) {
		// TODO Auto-generated method stub
		return orderservice.getOrderid(transactionid);
	}

	/**
	 * Method to cancel the order 
	 */
	@Override
	public boolean cancelorder(int orderid) {
		// TODO Auto-generated method stub
		return orderservice.cancelorder(orderid);
	}

	/**
	 * method to update the status of the order
	 */
	@Override
	public boolean updateorder(OrderModel order) {
		// TODO Auto-generated method stub
		return orderservice.updateorder(order);
	}

}

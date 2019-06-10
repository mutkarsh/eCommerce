package com.globant.ecommerce.service;

import java.util.List;

import com.globant.ecommerce.models.OrderModel;

/**
 * 
 * @author utkarsh.mandade
 *
 */
public interface OrderService {

	/**
	 * this will return list of products for a user orders
	 * 
	 * @param userid
	 * @return
	 */
	public List<OrderModel> viewOrder(int userid);

	/**
	 * 
	 * @param order
	 * @return
	 */
	public boolean addOrder(OrderModel order);

	/**
	 * 
	 * @param orderid
	 * @return
	 */
	public List<OrderModel> viewOrderByOrderId(int orderid);

	/**
	 * 
	 * @param transactionid
	 * @return
	 */
	public int getOrderid(String transactionid);

	/**
	 * 
	 * @param orderid
	 * @return
	 */
	public boolean cancelorder(int orderid);
	
	/**
	 * 
	 * @param order
	 * @return
	 */
	public boolean updateorder(OrderModel order);


}

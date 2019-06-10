package com.globant.ecommerce.dao;

import java.util.List;

import com.globant.ecommerce.models.OrderModel;

/**
 * 
 * @author utkarsh.mandade
 *
 */
public interface OrderDao {

	/**
	 * Method to show ordered products for the specific user 
	 * @param userid
	 * @return
	 */
	public List<OrderModel> viewOrder(int userid);

	/**
	 * method to add order
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

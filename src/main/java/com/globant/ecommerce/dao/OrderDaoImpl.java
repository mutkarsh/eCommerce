package com.globant.ecommerce.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.globant.ecommerce.models.OrderModel;
import com.globant.ecommerce.models.ProductModel;

/**
 * 
 * @author utkarsh.mandade
 *
 */
@Service
public class OrderDaoImpl implements OrderDao {

	@Autowired
	JdbcTemplate jdbctemplate;

	/**
	 * Fetch All orders from DB of specific user
	 */
	@Override
	public List<OrderModel> viewOrder(int userid) {

		String QUERY = "select orderid,userid,totalamount,address,deliverystatus,paymentstatus,expecteddelivery,transactionid,status from orders where userid=?";
		Object param[] = { userid, };
		List<OrderModel> orders = new ArrayList<OrderModel>();
		List<ProductModel> products = new ArrayList<ProductModel>();
		try {

			orders = jdbctemplate.query(QUERY, param, new BeanPropertyRowMapper<OrderModel>(OrderModel.class));
			for (OrderModel orderModel : orders) {
				String QUERY1 = "select productid,quantity,price from products where orderid=?";
				Object param1[] = { orderModel.getOrderid() };
				products = jdbctemplate.query(QUERY1, param1,
						new BeanPropertyRowMapper<ProductModel>(ProductModel.class));
				orderModel.setProducts(products);
			}
			return orders;
		} catch (Exception e) {
			//  handle exception
			return null;
		}
		
	}

	/**
	 * Add a new order made
	 */
	@Override
	public boolean addOrder(OrderModel order) {

		String QUERY = "insert into orders(userid,address,totalamount,paymentstatus,deliverystatus,expecteddelivery,transactionid,status) values(?,?,?,?,?,?,?,?)";
		Object param[] = { order.getUserid(), order.getAddress(), order.getTotalamount(), order.getPaymentstatus(),
				order.getDeliverystatus(), order.getExpecteddelivery(), order.getTransactionid(), "Confirmed" };
		int result = 0;
		try {

			result = jdbctemplate.update(QUERY, param);

			int orderid = getOrderid(order.getTransactionid());

			String QUERY1 = "insert into products(productid,quantity,price,orderid) values(?,?,?,?)";
			List<ProductModel> products = order.getProducts();
			for (ProductModel productModel : products) {
				Object param1[] = { productModel.getProductid(), productModel.getQuantity(), productModel.getPrice(), orderid };
				jdbctemplate.update(QUERY1, param1);
			}
			return result > 0 ? true : false;
		} catch (Exception e) {
			//  handle exception
			return false;
		}
		
	}

	/**
	 * method to retrive a order by orderid
	 */
	@Override
	public List<OrderModel> viewOrderByOrderId(int orderid) {

		String QUERY = "select * from orders where orderid=? and status=?";
		Object param[] = { orderid, "confirmed" };
		List<OrderModel> orders = new ArrayList<OrderModel>();
		List<ProductModel> products = new ArrayList<ProductModel>();
		try {

			orders = jdbctemplate.query(QUERY, param, new BeanPropertyRowMapper<OrderModel>(OrderModel.class));
			for (OrderModel orderModel : orders) {
				String QUERY1 = "select productid,quantity,price from products where orderid=?";
				Object param1[] = { orderModel.getOrderid() };
				products = jdbctemplate.query(QUERY1, param1,
						new BeanPropertyRowMapper<ProductModel>(ProductModel.class));
				orderModel.setProducts(products);
			}
			return orders;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}

	/**
	 * Method to get orderid by transactionid
	 */
	@Override
	public int getOrderid(String transactionid) {

		String QUERY = "select orderid from orders where transactionid=? and status=?";
		Object param[] = { transactionid, "confirmed" };
		int orderid = -1;
		try {
			orderid = jdbctemplate.queryForObject(QUERY, param, Integer.class);
			return orderid;
		} catch (Exception e) {
			// TODO: handle exception
			return -1;
		}

		
	}

	/**
	 * Method to cancel the order
	 */
	@Override
	public boolean cancelorder(int orderid) {
		String QUERY = "update orders set status=? where orderid=? and status!=?";
		Object param[] = { "Cancelled", orderid ,"Cancelled"};
		int i = 0;
		try {
			i = jdbctemplate.update(QUERY, param);
			return i > 0 ? true : false;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}

	/**
	 * method to update the status of the order
	 */
	@Override
	public boolean updateorder(OrderModel order) {

		String QUERY = "update orders set deliverystatus=? ,paymentstatus=?, expecteddelivery=? where orderid=?";
		Object param[] = { order.getDeliverystatus(), order.getPaymentstatus(), order.getExpecteddelivery(), order.getOrderid() };
		int i =0;
		try {
			i = jdbctemplate.update(QUERY, param);
			return i > 0 ? true : false;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
}

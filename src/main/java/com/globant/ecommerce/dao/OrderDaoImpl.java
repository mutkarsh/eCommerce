package com.globant.ecommerce.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.globant.ecommerce.models.OrderModel;
import com.globant.ecommerce.models.ProductModel;
import com.globant.ecommerce.response.Response;

@Service
public class OrderDaoImpl implements OrderDao{
	
	@Autowired
	JdbcTemplate jdbctemplate;

	@Override
	public List<OrderModel> viewOrder(int userid) {
		
		String QUERY= "select orderid,userid,totalamount,address,deliverystatus,paymentstatus,expecteddelivery from orders where userid=?";
		Object param[] = { userid }; 
		List<OrderModel> om = jdbctemplate.query(QUERY,param, new BeanPropertyRowMapper<OrderModel>(OrderModel.class));
		for (OrderModel orderModel : om) {
			String QUERY1= "select productid,quantity,price from products where orderid=?";
			Object param1[]= {orderModel.getOrderid()};
			List<ProductModel> pm = jdbctemplate.query(QUERY1,param1, new BeanPropertyRowMapper<ProductModel>(ProductModel.class));
			orderModel.setProducts(pm);
		}
		
		return om;
	}

//	@Override
	public void addOrder(OrderModel order, List<ProductModel> products) {
		String QUERY= "insert into orders(userid,address,totalamount,paymentstatus,deliverystatus) values(?,?,?,?,?)";
		Object param[]= { order.getUserid(),order.getAddress(),order.getTotalamount(),order.getPaymentstatus(),order.getDeliverystatus() };
		jdbctemplate.update(QUERY,param);
		
		
		
	}
//
//	@Override
//	public void updateOrder(OrderModel order) {
//
//		String QUERY = "update orders set status=? ,cancel=? ,ret=? ,track=? ,payment=? where orderid=?";
//		Object param[]= {order.isOrderstatus(),order.isCancel(),order.isRet(),order.getTrack(),order.getPaymentmode()};
//		jdbctemplate.update(QUERY,param);
//	}
//
//	@Override
//	public void cancelorder(int orderid) {
//		String QUERY = "update orders set status=? where orderid=?";
//		Object param[]= {orderid};
//		jdbctemplate.update(QUERY,param);
//	}
	
}

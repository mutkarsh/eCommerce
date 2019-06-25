package com.globant.ecommerce.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.globant.ecommerce.facade.OrderFacadeImpl;
import com.globant.ecommerce.models.OrderModel;
import com.globant.ecommerce.models.ProductModel;
import com.globant.ecommerce.response.PlacedOrderResponse;
import com.globant.ecommerce.response.Response;

/**
 * Main Controller
 * 
 * @author utkarsh.mandade
 *
 */
@RestController
public class RestOrderController {

	@Autowired
	OrderFacadeImpl orderservice;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Method to show all orders
	 * 
	 * @param userid
	 * @param authToken
	 * @return
	 */
	@GetMapping("/order/{userid}")
	public ResponseEntity<List<OrderModel>> viewOrder(@PathVariable("userid") int userid,
			@RequestHeader(value = "authToken", defaultValue = "") String authToken) {

//		Response resp = new Response();

		// check validity based on user authToken
		
		if (authentication(authToken)) {
			
			List<OrderModel> orders = orderservice.viewOrder(userid);
			if (orders.size() == 0) {
				
				HttpHeaders headers = new HttpHeaders();
			    headers.add("Message", "not a valid userID");
			    return new ResponseEntity<>(null, headers, HttpStatus.NO_CONTENT);	
//				resp.setMessage("Invalid userid");
//				resp.setStatusCode("401");
//				return resp;
			}
			HttpHeaders headers = new HttpHeaders();
		    headers.add("Message", "Your Orders");
		    return new ResponseEntity<>(orders, headers, HttpStatus.OK);

//			resp.setMessage("Your Orders");
//			resp.setStatusCode("200");
//			resp.setData(orders);
//			return resp;
		} else {


			HttpHeaders headers = new HttpHeaders();
		    headers.add("Message", "Authentication Failed");
		    return new ResponseEntity<>(null, headers, HttpStatus.UNAUTHORIZED);

//			resp.setMessage("user not Logged In");
//			resp.setStatusCode("401");
//			return resp;
		}

	}

	/**
	 * method to add order in the db
	 * 
	 * @param userid
	 * @param data
	 * @param authToken
	 * @return
	 */
	@PostMapping("/order/placeorder/{userid}")
	public ResponseEntity<List<OrderModel>> placeOrder(@PathVariable("userid") int userid, @RequestBody String data,
			@RequestHeader(value = "authToken", defaultValue = "") String authToken) {

//		PlacedOrderResponse response = new PlacedOrderResponse();

		// check validity based on user authToken
		if (authentication(authToken)) {
			JSONObject object = new JSONObject(data);
			OrderModel order = new OrderModel();
			List<ProductModel> products = new ArrayList<ProductModel>();
			order.setUserid(object.getInt("userId"));
			order.setAddress(object.getString("address"));
			order.setTransactionid(object.getString("transactionId"));
			order.setTotalamount(object.getDouble("totalAmount"));
			order.setPaymentstatus(object.getString("paymentStatus"));
			JSONArray objectarray = object.getJSONArray("products");

			for (int i = 0; i < objectarray.length(); ++i) {
				JSONObject obj = objectarray.getJSONObject(i);
				products.add(new ProductModel(obj.getInt("productId"), obj.getInt("quantity"), obj.getDouble("price")));
			}

			order.setProducts(products);
			boolean status = orderservice.addOrder(order);

			if (!status) {
				HttpHeaders headers = new HttpHeaders();
			    headers.add("Message", "Something went Wrong! Please try again.");
			    return new ResponseEntity<>(null, headers, HttpStatus.NO_CONTENT);
//				response.setMessage("Something is Wrong! Please try again.");
//				response.setStatusCode("401");
//				return response;
			}

			 HttpHeaders headers = new HttpHeaders();
			    headers.add("Message", "Order Placed Successfully");
			    List<OrderModel> orders = orderservice.viewOrderByOrderId(orderservice.getOrderid(order.getTransactionid()));
			    return new ResponseEntity<>(orders, headers, HttpStatus.OK);

//			response.setMessage("Order Placed Successfully");
//			response.setData(orderservice.viewOrderByOrderId(orderservice.getOrderid(order.getTransactionid())));
//			response.setStatusCode("200");
//			return response;
		} else {

			HttpHeaders headers = new HttpHeaders();
		    headers.add("Message", "Authentication Failed, User not Logged in");
		    return new ResponseEntity<>(null, headers, HttpStatus.UNAUTHORIZED);
//			response.setMessage("user not Logged In");
//			response.setStatusCode("401");
//			return response;
			
		}

	}

	/**
	 * method to cancel the order.
	 * 
	 * @param orderid
	 * @param authToken
	 * @return
	 */
	@PostMapping("/order/cancel/{orderid}")
	public ResponseEntity<String> cancelOrder(@PathVariable("orderid") int orderid,
			@RequestHeader(value = "authToken", defaultValue = "") String authToken) {

//		Response response = new Response();

		// check validity based on user authToken
		if (authentication(authToken)) {
			
			boolean status = orderservice.cancelorder(orderid);
			if(!status) {

				HttpHeaders headers = new HttpHeaders();
			    headers.add("Message", "order already canceled");
			    return new ResponseEntity<>("", headers, HttpStatus.NO_CONTENT);

//				response.setMessage("Something Went Wrong! Plz try again.");
//				response.setStatusCode("401");
//				return response;
			}
			HttpHeaders headers = new HttpHeaders();
			headers.add("Message", "Order Canceled Successfully");
			OrderModel order = new OrderModel();
			order.setOrderid(orderid);
			return new ResponseEntity<>("", headers, HttpStatus.OK);
		    
//			response.setMessage("Order Cancelled Successfully");
//			response.setStatusCode("200");
//			List<OrderModel> orders = new ArrayList<OrderModel>();
//			OrderModel order = new OrderModel();
//			order.setOrderid(orderid);
//			response.setData(orders);
//			return response;
		} 
		else {
			HttpHeaders headers = new HttpHeaders();
		    headers.add("Message", "Authentication Failed/User not Logged in");
		    return new ResponseEntity<>("", headers, HttpStatus.UNAUTHORIZED);
//			response.setMessage("user not Logged In");
//			response.setStatusCode("401");
//			return response;
		}
	}

	/**
	 * method to update the order status
	 * 
	 * @param orderid
	 * @param data
	 * @param authToken
	 * @return
	 */
	@PostMapping("/order/updateOrder/{orderid}")
	public ResponseEntity<OrderModel> updateOrder(@PathVariable("orderid") int orderid, @RequestBody String data,
			@RequestHeader(value = "authToken", defaultValue = "") String authToken) {

		Response response = new Response();
		// check validity based on user authToken
		if (authentication(authToken)) {

			JSONObject object = new JSONObject(data);
			OrderModel order = new OrderModel();
			order.setOrderid(object.getInt("orderid"));
			order.setDeliverystatus(object.getString("deliveryStatus"));
			order.setPaymentstatus(object.getString("paymentStatus"));
			order.setExpecteddelivery(object.getString("expectedDelivery"));

			boolean status = orderservice.updateorder(order);
			if(!status) {

				HttpHeaders headers = new HttpHeaders();
			    headers.add("Message", "order not found");
			    return new ResponseEntity<>(null, headers, HttpStatus.NO_CONTENT);

//				response.setMessage("Something Went Wrong! Plz try again.");
//				response.setStatusCode("401");
//				return response;
			}
			HttpHeaders headers = new HttpHeaders();
			headers.add("Message", "Order Updated Successfully");
			return new ResponseEntity<>(order, headers, HttpStatus.OK);
//			response.setMessage("Order Updated Successfully");
//			response.setData(orders);
//			response.setStatusCode("200");
//			return response;

		} else {
			HttpHeaders headers = new HttpHeaders();
		    headers.add("Message", "Authentication Failed/User not Logged in");
		    return new ResponseEntity<>(null, headers, HttpStatus.UNAUTHORIZED);
//			response.setMessage("user not Logged In");
//			response.setStatusCode("401");
//			return response;
		}
	}

	/**
	 * Authentication service for checking valid user using authenticationToken
	 * 
	 * @param authToken
	 * @return
	 */
	public boolean authentication(String authToken) {


		try {
		//Temporary auth Response
		if(true) return true;
		final String url = "http://192.168.43.163:8080/checklogin";
//		final String url = "http://ecommerce/checklogin";
		HttpHeaders headers = new HttpHeaders();
		headers.set("authToken", authToken);
		System.out.println("checking ....Auth Token");
		HttpEntity entity = new HttpEntity(headers);
		ResponseEntity<String> resp = restTemplate.exchange(
		   url, HttpMethod.GET, entity, String.class);
		System.out.println("checked");
		System.out.println(resp.getStatusCodeValue());
		if(resp.getStatusCodeValue()==200) return true;
		return false;
		}
		catch(Exception e) {
			return false;
		}
	}
}

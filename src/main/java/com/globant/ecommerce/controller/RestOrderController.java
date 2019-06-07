package com.globant.ecommerce.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.globant.ecommerce.models.OrderModel;
import com.globant.ecommerce.models.ProductModel;
import com.globant.ecommerce.response.PlacedOrderResponse;
import com.globant.ecommerce.response.Response;
import com.globant.ecommerce.service.OrderServiceImpl;

@RestController
public class RestOrderController {

	@Autowired
	OrderServiceImpl orderservice;
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/createOrder/{userid}")
	public void createorder(@PathVariable("userid") int userid,
			@RequestHeader(value = "authToken", defaultValue = "") String authToken) {
		System.out.println("HIT.........");
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("userid", userid);
		ResponseEntity<Object> resp = restTemplate.getForEntity("http://10.221.1.73:8080/viewCart/{userid}",
				Object.class, param);
		JSONObject data = new JSONObject(resp);
		String data1 = resp.toString();
		System.out.println(resp.getBody());

//		JSONObject outerArray = new JSONObject(resp);
//		JSONArray inner = outerArray.getJSONArray("");
//		for(int i = 0;i<outerArray.length();i++) {
//			JSONObject data = inner.getJSONObject(i);
//			System.out.println(data);			
//		}
//		List<JSONObject> listdata = resp.getBody();

		OrderModel orders = new OrderModel();
//		orderservice.addOrder();

	}

	@GetMapping("/hello")
	public String hello() {
		return "HELLO";
	}

	@GetMapping("/viewOrder/{userid}")
	public Response viewOrder(@PathVariable("userid") int userid,
			@RequestHeader(value = "authToken", defaultValue = "") String authToken) {

		// add auth method to all
		Response resp = new Response();
		if (authentication(authToken)) {
			List<OrderModel> om = orderservice.viewOrder(userid);
			resp.setMessage("Your Orders");
			resp.setStatusCode("200");
			resp.setData(om);
			return resp;
		} else {

			resp.setMessage("user not Logged In");
			resp.setStatusCode("401");
			return resp;
		}

	}

	@PostMapping("/placeorder/{userid}")
	public PlacedOrderResponse placeOrder(@PathVariable("userid") int userid, @RequestBody String data,
			@RequestHeader(value = "authToken", defaultValue = "") String authToken) {
		PlacedOrderResponse response = new PlacedOrderResponse();
		if(authentication(authToken)) {
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
		orderservice.addOrder(order);

//		PlacedOrderResponse response = new PlacedOrderResponse();
		response.setMessage("Order Placed Successfully");
		response.setData(orderservice.viewOrderByOrderId(orderservice.getOrderid(order.getTransactionid())));
		response.setStatusCode("200");
		return response;
		}
		else {
			
			response.setMessage("user not Logged In");
			response.setStatusCode("401");
			return response;
		}
		
	}

	@PostMapping("/cancelorder/{orderid}")
	public Response cancelOrder(@PathVariable("orderid") int orderid,
			@RequestHeader(value = "authToken", defaultValue = "") String authToken) {

		Response response = new Response();
		if(authentication(authToken)) {
		boolean bool = orderservice.cancelorder(orderid);
		response.setMessage("Order Cancelled Successfully");
		response.setStatusCode("200");
		List<OrderModel> orders = new ArrayList<OrderModel>();
		OrderModel order = new OrderModel();
		order.setOrderid(orderid);
		response.setData(orders);
		return response;
		}
		else {
			
			response.setMessage("user not Logged In");
			response.setStatusCode("401");
			return response;
		}
	}

	public boolean authentication(String authToken) {

		String url = "http://192.168.43.163:8080/checklogin";
		HttpHeaders headers = new HttpHeaders();
		headers.set("authToken", authToken);
		HttpEntity entity = new HttpEntity(headers);
		ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		JSONObject jo = new JSONObject(resp.getBody());
		String statusCode = jo.getString("statusCode");
//		System.out.println(statusCode);
		if (statusCode.equals("200")) {
			return true;
		} else {
			System.out.println("User Not Logged In");
			return false;
		}
	}

}

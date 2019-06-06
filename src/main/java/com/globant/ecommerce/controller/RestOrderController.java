package com.globant.ecommerce.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.globant.ecommerce.models.OrderModel;
import com.globant.ecommerce.response.Response;
import com.globant.ecommerce.service.OrderService;
import com.globant.ecommerce.service.OrderServiceImpl;

@RestController
public class RestOrderController {

	@Autowired
	OrderServiceImpl orderservice;
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/createOrder/{userid}")
	public void createorder(@PathVariable("userid") int userid) {
		System.out.println("HIT.........");
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("userid", userid);
		ResponseEntity<Object> resp = restTemplate.getForEntity("http://10.221.1.73:8080/viewCart/{userid}",Object.class,param);
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
	public Response viewOrder(@PathVariable("userid")int userid){
		
		List<OrderModel> om = orderservice.viewOrder(userid);
		Response resp = new Response();
		resp.setMsg("Your Orders");
		resp.setStatus("200");
		resp.setOrder(om);
		
		return resp;
	
	}
	
}

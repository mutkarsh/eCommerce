package com.globant.ecommerce.response;

import java.util.List;

import com.globant.ecommerce.models.OrderModel;

public class Response {

	private String msg;
	private String status;
	private List<OrderModel> order;
	
	public Response() {
	}

	public Response(String msg, String status, List<OrderModel> order) {
		super();
		this.msg = msg;
		this.status = status;
		this.order = order;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OrderModel> getOrder() {
		return order;
	}

	public void setOrder(List<OrderModel> order) {
		this.order = order;
	}
	
	
 }

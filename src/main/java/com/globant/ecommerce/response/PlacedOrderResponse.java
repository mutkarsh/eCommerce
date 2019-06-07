package com.globant.ecommerce.response;

import java.util.List;

import com.globant.ecommerce.models.OrderModel;

public class PlacedOrderResponse {
	
	private String Message;
	private String StatusCode;
	private List<OrderModel> Data;
	
	public PlacedOrderResponse() {
		// TODO Auto-generated constructor stub
	}

	public PlacedOrderResponse(String message, String statusCode, List<OrderModel> data) {
		super();
		Message = message;
		StatusCode = statusCode;
		Data = data;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getStatusCode() {
		return StatusCode;
	}

	public void setStatusCode(String statusCode) {
		StatusCode = statusCode;
	}

	public List<OrderModel> getData() {
		return Data;
	}

	public void setData(List<OrderModel> data) {
		Data = data;
	}
	
	

}

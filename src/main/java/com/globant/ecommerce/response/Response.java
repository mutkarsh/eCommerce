package com.globant.ecommerce.response;

import java.util.List;

import com.globant.ecommerce.models.OrderModel;

/**
 * 
 * @author utkarsh.mandade
 *
 */
public class Response {

	private String Message;
	private String StatusCode;
	private List<OrderModel> Data;

	public Response() {
	}

	public Response(String message, String statusCode, List<OrderModel> data) {
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

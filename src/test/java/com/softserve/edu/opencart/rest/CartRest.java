package com.softserve.edu.opencart.rest;

import com.softserve.edu.opencart.tools.RestRequest;

public class CartRest {

	public String addToCart(String token, String productId, String quantuty) {
		return null;
	}

	public String getAllFromCart(String token) {
		String tokenParams = "token=" + token;
		RestRequest restRequest = new RestRequest();
		//return restRequest.sendRequestPost("http://192.168.103.210/opencart/upload/index.php?route=api/cart/products", tokenParams);
		return restRequest.sendRequestGet("http://192.168.103.210/opencart/upload/index.php?route=api/cart/products&token=Ze6zE0rtz1BAGXswTnOUX5IxXw6ypkDg");
	}

}

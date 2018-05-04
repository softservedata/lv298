package com.softserve.edu.opencart.tests;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

class CartProduct {
	private String product_id;
	private String name;
}

class Cart {
	private String products;
	private String vouchers;
	private String totals;
	
	public String getProducts() {
		return products;
	}
	public void setProducts(String products) {
		this.products = products;
	}
	public String getVouchers() {
		return vouchers;
	}
	public void setVouchers(String vouchers) {
		this.vouchers = vouchers;
	}
	public String getTotals() {
		return totals;
	}
	public void setTotals(String totals) {
		this.totals = totals;
	}

	public String toString() {
		return "products: " + products + "\nvouchers: " + vouchers + "\ntotals: " + totals;
	}
	
}

public class OkTest {

	private String token;
	private String phpSessId;
	
	public void Login() throws Exception {
		OkHttpClient client = new OkHttpClient();
		//
		MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
		RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"username\"\r\n\r\nlv298\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"key\"\r\n\r\nyFfTWUqt6dRyQAFpBsdwctgMGViK3ewtGY0FJCH3gyISdvkeIb8U8mvqiFm1WjJhwJF3M1x4NFAdoutTjpL7DkO54yoQFdmAnAVfqU3DgHtMKnuypPnLCcoFmrFK8RK5D1SyJFkfeJDsd3A0Q00a2hQAnNlGztQ36Q2PQO0yRCfQCVxPUfFkrUOeibFjYpgtvXW7HsAM5q6HrFmmZ3XKsdTIPTYlKlb38lciZAInSTd50vSYoeZQQPlDnqWHqdHc\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
		Request request = new Request.Builder()
		  .url("http://192.168.103.210/opencart/upload/index.php?route=api/login")
		  .post(body)
		  .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
		  .addHeader("Content-Type", "application/json")
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Postman-Token", "46772ac7-0ea9-4caf-a13e-fcd2014f3195")
		  .build();
		//
		Response response = client.newCall(request).execute();
		//
		String text = response.body().string();
		System.out.println(text);
		System.out.println(response.headers("Set-Cookie"));
		System.out.println(response.headers("Set-Cookie").get(0));
		phpSessId = response.headers("Set-Cookie").get(0);
		//
		Matcher matcher = Pattern.compile("\"token\":\"[^\"]{32}\"").matcher(text);
		if (matcher.find()) {
			token = text.substring(matcher.start()+9, matcher.end()-1);
        }
		System.out.println("token: " + token);
	}
	
	public String getCart() throws Exception {
		OkHttpClient client = new OkHttpClient();
		RequestBody formBody = new FormBody.Builder()
			      //.add("username", "test")
			      //.add("password", "test")
			      .build();
		Request request = new Request.Builder()
		  //.url("http://192.168.103.210/opencart/upload/index.php?route=api/cart/products&token=aF4RZlrvCZfQAAbscLCSwZjwdm8IzNsw")
   		  .url("http://192.168.103.210/opencart/upload/index.php?route=api/cart/products&token="+token)
		  .post(formBody)
		  //.addHeader("Cache-Control", "no-cache")
		  //.addHeader("Postman-Token", "202bdba9-3cc8-462c-952b-d916469de416")
		  //.addHeader("Cookie", "PHPSESSID=fg06tf2ar502vmcv08777f94a2; path=/; HttpOnly")
		  .addHeader("Cookie", phpSessId)
		  .build();
		
		Response response = client.newCall(request).execute();
		String text = response.body().string();
		System.out.println(text);
		return text;
	}
	
	public String addToCart() throws Exception {
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
		RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"product_id\"\r\n\r\n43\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"quantuty\"\r\n\r\n1\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
		Request request = new Request.Builder()
		  .url("http://192.168.103.210/opencart/upload/index.php?route=api/cart/add&token="+token)
		  .post(body)
		  //.addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
		  //.addHeader("Cache-Control", "no-cache")
		  //.addHeader("Postman-Token", "6bb25abb-b6b6-45dd-84de-5ef8f71c9c16")
		  .addHeader("Cookie", phpSessId)
		  .build();

		Response response = client.newCall(request).execute();
		String text = response.body().string();
		System.out.println(text);
		return text;
	}
	
	@Test
	public void test1() throws Exception {
		Login();
		addToCart();
		String cartJsonResult = getCart();
		Assert.assertTrue(cartJsonResult.contains("\"product_id\":\"43\""));
		Assert.assertTrue(cartJsonResult.contains("\"quantity\":\"1\""));
		//
//		ObjectMapper mapper = new ObjectMapper();
//		//JSON from String to Object
//		Cart cart = mapper.readValue(cartJsonResult.replaceAll("\"", "'"), Cart.class);
		//
//		Gson gson = new Gson();
//		Cart cart= gson.fromJson(cartJsonResult, Cart.class);
		//
//		System.out.println(cart);
	}
	
}

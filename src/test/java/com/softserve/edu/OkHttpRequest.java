package com.softserve.edu;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpRequest {

	public static void main(String[] args) throws Exception {
//		OkHttpClient client = new OkHttpClient();
//
//		MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
//		RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"username\"\r\n\r\nlv298\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"key\"\r\n\r\nyFfTWUqt6dRyQAFpBsdwctgMGViK3ewtGY0FJCH3gyISdvkeIb8U8mvqiFm1WjJhwJF3M1x4NFAdoutTjpL7DkO54yoQFdmAnAVfqU3DgHtMKnuypPnLCcoFmrFK8RK5D1SyJFkfeJDsd3A0Q00a2hQAnNlGztQ36Q2PQO0yRCfQCVxPUfFkrUOeibFjYpgtvXW7HsAM5q6HrFmmZ3XKsdTIPTYlKlb38lciZAInSTd50vSYoeZQQPlDnqWHqdHc\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
//		Request request = new Request.Builder()
//		  .url("http://192.168.103.210/opencart/upload/index.php?route=api/login")
//		  .post(body)
//		  .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
//		  .addHeader("Content-Type", "application/json")
//		  .addHeader("Cache-Control", "no-cache")
//		  .addHeader("Postman-Token", "46772ac7-0ea9-4caf-a13e-fcd2014f3195")
//		  .build();
//
//		Response response = client.newCall(request).execute();
//		//
//		System.out.println(response.body().string());
//		System.out.println(response.headers("Set-Cookie"));
//		System.out.println(response.headers("Set-Cookie").get(0));
		//
		//
		OkHttpClient client = new OkHttpClient();
		RequestBody formBody = new FormBody.Builder()
			      //.add("username", "test")
			      //.add("password", "test")
			      .build();
		Request request = new Request.Builder()
		  .url("http://192.168.103.210/opencart/upload/index.php?route=api/cart/products&token=aF4RZlrvCZfQAAbscLCSwZjwdm8IzNsw")
		  .post(formBody)
		  //.addHeader("Cache-Control", "no-cache")
		  //.addHeader("Postman-Token", "202bdba9-3cc8-462c-952b-d916469de416")
		  .addHeader("Cookie", "PHPSESSID=fg06tf2ar502vmcv08777f94a2; path=/; HttpOnly")
		  .build();
		
		Response response = client.newCall(request).execute();
		System.out.println(response.body().string());
	}
	
}

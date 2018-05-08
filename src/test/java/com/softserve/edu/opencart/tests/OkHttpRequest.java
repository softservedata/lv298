package com.softserve.edu.opencart.tests;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.io.IOException;

public class OkHttpRequest {

	@Test
	public void okTest() throws Exception{
		String expected = "{\"token\":\"QpwL5tke4Pnpja7X\"}";
		String actual = "";
		OkHttpClient client = new OkHttpClient();
		RequestBody formBody = new FormBody.Builder()
				.add("email", "sydney@fife")
				.add("password", "pistol")
				.build();
		Request request = new Request.Builder()
				.url("https://reqres.in/api/register")
				.post(formBody)
				.build();

			Response response = client.newCall(request).execute();
			actual = response.body().string();
			//System.out.println(response.body().string());

		Assert.assertEquals(actual,expected);
	}
	
}
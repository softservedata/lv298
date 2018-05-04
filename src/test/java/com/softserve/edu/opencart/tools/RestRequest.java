package com.softserve.edu.opencart.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class RestRequest {
	private final String CONNECTION_ERROR = "Connection failed, server response %s";
	private final String REQUEST_ERROR = "Error reading data from response, details %s";
	
	private HttpURLConnection getHttpConnection(String urlRequest, String typeHttpMethod) {
		URL url = null;
		HttpURLConnection connection = null;
		try {
			url = new URL(urlRequest);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(typeHttpMethod); // type: POST, PUT, DELETE, GET
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setConnectTimeout(20000); // 20 secs
			connection.setReadTimeout(20000); // 20 secs
			//connection.setRequestProperty("Accept-Encoding", "Your Encoding");
			//connection.setRequestProperty("Content-Type", "Your Encoding");
		} catch (Exception e) {
			// TODO Develop Custom Exceptions
			throw new RuntimeException(String.format(CONNECTION_ERROR, e.toString()));
		}
		return connection;
	}

	private String getResponse(HttpURLConnection connection) {
		String result = new String();
		try {
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream inputStream = connection.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
				// InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "windows-1251");
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				String inputLine = null;
				while ((inputLine = bufferedReader.readLine()) != null) {
					result = result + inputLine;
				}
			}
		} catch (IOException e) {
			// TODO Develop Custom Exceptions
			throw new RuntimeException(String.format(REQUEST_ERROR, e.toString()));
		}
		System.out.println("Headers:" + connection.getHeaderFields());
		connection.disconnect();
		return result;
	}

	private void addRequestParameters(OutputStream outputStream, String... parameters) throws IOException {
		for (String currentParameter : parameters) {
			outputStream.write(currentParameter.getBytes());
		}
		outputStream.flush();
		outputStream.close();
	}
	
	public String sendRequestGet(String urlRequest) {
		HttpURLConnection connection = getHttpConnection(urlRequest, "GET");
		try {
			connection.connect();
		} catch (IOException e) {
			// TODO Develop Custom Exceptions
			throw new RuntimeException(String.format(REQUEST_ERROR, e.toString()));
		}
		return getResponse(connection);
	}

	public String sendRequestPostLogin(String urlRequest) {
		HttpURLConnection connection = getHttpConnection(urlRequest, "POST");
		try {
			connection.setRequestProperty("Content-Type", "application/json");
			//connection.setRequestProperty("username","lv298");
			//connection.setRequestProperty("key","yFfTWUqt6dRyQAFpBsdwctgMGViK3ewtGY0FJCH3gyISdvkeIb8U8mvqiFm1WjJhwJF3M1x4NFAdoutTjpL7DkO54yoQFdmAnAVfqU3DgHtMKnuypPnLCcoFmrFK8RK5D1SyJFkfeJDsd3A0Q00a2hQAnNlGztQ36Q2PQO0yRCfQCVxPUfFkrUOeibFjYpgtvXW7HsAM5q6HrFmmZ3XKsdTIPTYlKlb38lciZAInSTd50vSYoeZQQPlDnqWHqdHc");
			//
			OutputStream os = connection.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
			writer.write("{'username'='lv298', 'key'='yFfTWUqt6dRyQAFpBsdwctgMGViK3ewtGY0FJCH3gyISdvkeIb8U8mvqiFm1WjJhwJF3M1x4NFAdoutTjpL7DkO54yoQFdmAnAVfqU3DgHtMKnuypPnLCcoFmrFK8RK5D1SyJFkfeJDsd3A0Q00a2hQAnNlGztQ36Q2PQO0yRCfQCVxPUfFkrUOeibFjYpgtvXW7HsAM5q6HrFmmZ3XKsdTIPTYlKlb38lciZAInSTd50vSYoeZQQPlDnqWHqdHc'}");
			//writer.write("key=yFfTWUqt6dRyQAFpBsdwctgMGViK3ewtGY0FJCH3gyISdvkeIb8U8mvqiFm1WjJhwJF3M1x4NFAdoutTjpL7DkO54yoQFdmAnAVfqU3DgHtMKnuypPnLCcoFmrFK8RK5D1SyJFkfeJDsd3A0Q00a2hQAnNlGztQ36Q2PQO0yRCfQCVxPUfFkrUOeibFjYpgtvXW7HsAM5q6HrFmmZ3XKsdTIPTYlKlb38lciZAInSTd50vSYoeZQQPlDnqWHqdHc");
			writer.flush();
			writer.close();
			os.close();
			//
			connection.connect();
			//addRequestParameters(connection.getOutputStream(), parameters);
		} catch (IOException e) {
			// TODO Develop Custom Exceptions
			throw new RuntimeException(String.format(REQUEST_ERROR, e.toString()));
		}
		return getResponse(connection);
	}
	
	public String sendRequestPost(String urlRequest, String... parameters) {
		HttpURLConnection connection = getHttpConnection(urlRequest, "POST");
		try {
			//connection.connect();
			// ++++++++++++
			//connection.setRequestProperty("Content-Type", "application/json");
			//connection.setRequestProperty("Authorization", "Token token=7LlqzyLeo4ZjLxoTyoe9zhGf6wNJM0Iu");
			//connection.addRequestProperty("Authorization ", "token=7LlqzyLeo4ZjLxoTyoe9zhGf6wNJM0Iu");
			//connection.addRequestProperty("token ", "7LlqzyLeo4ZjLxoTyoe9zhGf6wNJM0Iu");
			//connection.setRequestProperty("route","api/cart/products");
			connection.setRequestProperty("token","SwatftSU5lGyzBw5kZ0cX31t9fKGBGK9");
			connection.setRequestProperty("Cookie","PHPSESSID=1i3ddc883rv189lirrg7hqj3b7;");
			//connection.setRequestProperty("Set-Cookie","PHPSESSID=id66d91iqv65dnt52eu1lu3mm6; path=/; HttpOnly");
			//connection.setRequestProperty("Set-Cookie","api=905793946f946171e6a765cee8; path=/; httponly");
			//
			//connection.setRequestProperty("Accept", "application/json");
//			OutputStream os = connection.getOutputStream();
//			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
//			writer.write("token=7LlqzyLeo4ZjLxoTyoe9zhGf6wNJM0Iu");
//			writer.flush();
//			writer.close();
//			os.close();
			//
//			OutputStream os = connection.getOutputStream();
//			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
//			writer.write("{'route':'api/cart/products','token':'7LlqzyLeo4ZjLxoTyoe9zhGf6wNJM0Iu'}");
//			writer.flush();
//			writer.close();
//			os.close();
			
			connection.connect();
			//addRequestParameters(connection.getOutputStream(), parameters);
		} catch (IOException e) {
			// TODO Develop Custom Exceptions
			throw new RuntimeException(String.format(REQUEST_ERROR, e.toString()));
		}
		return getResponse(connection);
	}

	public String sendRequestPut(String urlRequest, String... parameters) {
		HttpURLConnection connection = getHttpConnection(urlRequest, "PUT");
		try {
			connection.connect();
			addRequestParameters(connection.getOutputStream(), parameters);
		} catch (IOException e) {
			// TODO Develop Custom Exceptions
			throw new RuntimeException(String.format(REQUEST_ERROR, e.toString()));
		}
		return getResponse(connection);
	}

	public String sendRequestDelete(String urlRequest, String... parameters) {
		HttpURLConnection connection = getHttpConnection(urlRequest, "DELETE");
		try {
			connection.connect();
			addRequestParameters(connection.getOutputStream(), parameters);
		} catch (IOException e) {
			// TODO Develop Custom Exceptions
			throw new RuntimeException(String.format(REQUEST_ERROR, e.toString()));
		}
		return getResponse(connection);
	}

}

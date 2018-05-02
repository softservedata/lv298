package com.softserve.edu.opencart.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

	public String sendRequestPost(String urlRequest, String... parameters) {
		HttpURLConnection connection = getHttpConnection(urlRequest, "POST");
		try {
			connection.connect();
			addRequestParameters(connection.getOutputStream(), parameters);
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

package com.saas.goods.bo.test;

import java.net.URLEncoder;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestBase {
	
	public static JsonNode post(String request, String content) throws Exception{
		
		CloseableHttpClient client = HttpClientBuilder.create().build();
		
		HttpPost httpPost = new HttpPost("http://localhost:8080/" + request);
		
		httpPost.setHeader("Content-Type","application/json;charset=utf-8");
		
		StringEntity body = new StringEntity(content, "utf-8");
		
		httpPost.setEntity(body);
		
		CloseableHttpResponse response = client.execute(httpPost);
		
		int statusCode = response.getStatusLine().getStatusCode() ;
		if (statusCode!= 200) {
			throw new RuntimeException("request error, code:" + statusCode);
		}

		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode root =mapper.readTree(response.getEntity().getContent());
		
		System.out.println(root);
		
		return root;
	}

	
	public static JsonNode get(String request, Map<String, String> params) throws Exception{
		
		StringBuffer sb = new StringBuffer();
		
		for(String key : params.keySet()) {
			if(sb.length() > 0) {
				sb.append("&");
			}
			sb.append(key).append("=");
			String value = params.get(key);
			sb.append(URLEncoder.encode(value, "utf-8"));
		}
		
		CloseableHttpClient client = HttpClientBuilder.create().build();
		
		HttpGet httpGet = new HttpGet("http://localhost:8080/" + request + "?" + sb.toString());
		
		CloseableHttpResponse response = client.execute(httpGet);
		
		int statusCode = response.getStatusLine().getStatusCode() ;
		if (statusCode!= 200) {
			throw new RuntimeException("request error, code:" + statusCode);
		}

		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode root =mapper.readTree(response.getEntity().getContent());
		
		System.out.println(root);
		
		return root;
	}

}

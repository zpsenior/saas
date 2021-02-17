package com.saas.wpay;

import java.security.PrivateKey;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WPay {
	
	private final static String baseUrl = "https://api.mch.weixin.qq.com";
	
	private String mchid;
	
	private PrivateKey privateKey;
	
	private String serialNo;
	
	public void execute(WPayRequest request) throws Exception{
		String signature = request.signature(privateKey);
		String urlStr = baseUrl + request.getRequestStr();
		String body = request.getBody();
		String nonceStr = request.getNonceStr();
		long timestamp = request.getTimestamp();
		StringBuffer sb = new StringBuffer();
		sb.append("WECHATPAY2-SHA256-RSA2048 ");
		sb.append("mchid=").append(mchid);
		sb.append(",serial_no=").append(serialNo);
		sb.append(",nonce_str=").append(nonceStr);
		sb.append(",timestamp=").append(timestamp);
		sb.append(",signature=").append(signature);
		String Authorization = sb.toString();
		
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		
		CloseableHttpResponse response;
		
		String method = request.getMethod();
		
		if(method.equals(WPayRequest.GET)) {
			HttpGet httpGet = new HttpGet(urlStr);
			
			httpGet.setHeader("Authorization", Authorization);

			response = httpClient.execute(httpGet);
		}else if(method.equals(WPayRequest.POST)) {
			HttpPost httpPost = new HttpPost(urlStr);
			
			httpPost.setHeader("Authorization", Authorization);
			
			StringEntity entity = new StringEntity(body);
			entity.setContentType("UTF-8");
			
			httpPost.setEntity(entity);
			
			response = httpClient.execute(httpPost);
		}else if(method.equals(WPayRequest.PUT)) {
			HttpPut httpPut = new HttpPut(urlStr);
			
			httpPut.setHeader("Authorization", Authorization);
			
			response = httpClient.execute(httpPut);
		}else {
			throw new RuntimeException("can not support method:" + method);
		}
		
		StatusLine statusLine = response.getStatusLine();
		int code = statusLine.getStatusCode();

		if(code != HttpStatus.SC_OK){
			throw new RuntimeException("request error:" + code);
		}
		
		HttpEntity responseEntity = response.getEntity();
		
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode root = mapper.readTree(responseEntity.getContent());

	}

}

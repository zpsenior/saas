package com.saas.wpay;

import java.io.ByteArrayOutputStream;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saas.pub.SignUtils;

@Component
public class WPay implements Const {
	
	private final static String baseUrl = "https://api.mch.weixin.qq.com";
	
	@Value("${wpay.mchid}")
	private String mchid;
	
	@Value("${wpay.serialNo}")
	private String serialNo;

	@Autowired
	private Certificate cert;
	
	
	public JsonNode execute(WPayRequest request) throws Exception{

		String nonceStr = SignUtils.randomString(32);
		long timestamp  = System.currentTimeMillis()/1000;
		String method = request.getMethod();
		String body = request.buildBody();
		String requestStr = request.buildRequestStr();
		String signature = cert.doSign(method, requestStr, body, nonceStr, timestamp);
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
		
		String urlStr = baseUrl + requestStr;
		
		if(method.equals(GET)) {
			HttpGet httpGet = new HttpGet(urlStr);
			
			httpGet.setHeader("Authorization", Authorization);

			response = httpClient.execute(httpGet);
		}else if(method.equals(POST)) {
			HttpPost httpPost = new HttpPost(urlStr);
			
			httpPost.setHeader("Authorization", Authorization);
			
			StringEntity entity = new StringEntity(body);
			entity.setContentType("UTF-8");
			
			httpPost.setEntity(entity);
			
			response = httpClient.execute(httpPost);
		}else if(method.equals(PUT)) {
			HttpPut httpPut = new HttpPut(urlStr);
			
			httpPut.setHeader("Authorization", Authorization);
			
			StringEntity entity = new StringEntity(body);
			entity.setContentType("UTF-8");
			
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
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		
		responseEntity.writeTo(os);
		
		os.flush();
		
		body = os.toString("UTF-8");
		
		nonceStr = response.getFirstHeader("Wechatpay-Nonce").getValue();
		timestamp = Long.parseLong(response.getFirstHeader("Wechatpay-Timestamp").getValue());
		signature = response.getFirstHeader("Wechatpay-Signature").getValue();
		
		if(!cert.doVerify(timestamp, nonceStr, body, signature)) {
			throw new RuntimeException("verify error, signature:" + signature);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode root = mapper.readTree(body);
		
		return root;
	}
}

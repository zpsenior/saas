package com.saas.wpay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saas.pub.SignUtils;

@Component
public class WPayNotify extends HandlerInterceptorAdapter {
	
	@Autowired
	private Certificate cert;
	
	@Autowired(required=false)
	private NotifyCallback callback;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String body = doVerify(request);
		
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode root = mapper.readTree(body);
		
		JsonNode resource = root.get("resource");
		
		String cipherContent = resource.get("ciphertext").asText();
		String nonce = resource.get("nonce").asText();
		String associatedData = resource.get("associated_data").asText();
		
		String content = cert.decryptToString(associatedData.getBytes("UTF-8"), nonce.getBytes("UTF-8"), cipherContent);
		
		if(callback != null) {
			callback.process(content);
		}
		return false;
	}

	private String doVerify(HttpServletRequest request) throws Exception {
		long timestamp;
		String content;
		String nonceStr = request.getHeader("Wechatpay-Nonce");
		if(nonceStr == null) {
			throw new RuntimeException("nonceStr is null");
		}
		try{
			timestamp = Long.parseLong(request.getHeader("Wechatpay-Timestamp"));
		}catch(Throwable e) {
			throw new RuntimeException("timestamp is error");
		}
		String signature = request.getHeader("Wechatpay-Signature");
		if(signature == null) {
			throw new RuntimeException("signature is null");
		}
		try{
			content = new String(SignUtils.readBytes(request.getInputStream()), "UTF-8");
		}catch(Throwable e) {
			throw new RuntimeException("read content error:" + e);
		}
		
		boolean res = cert.doVerify(timestamp, nonceStr, content, signature);
		
		if(!res) {
			throw new RuntimeException("verify error, signature:" + signature);
		}
		return content;
	}

}

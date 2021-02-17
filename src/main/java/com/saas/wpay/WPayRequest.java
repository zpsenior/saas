package com.saas.wpay;

import java.lang.reflect.Field;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;


public abstract class WPayRequest {
	
	public final static String POST = "post";
	
	public final static String GET = "get";
	
	public final static String PUT = "put";
	
	private String url;
	private String method;
	
	private String body;
	
	private String requestStr;
	
	private String nonceStr;
	
	private long timestamp;
	
	private Set<String> pathArgs = new HashSet<>();
	
	protected WPayRequest(String url, String method) {
		this.url = "/v3/" + url;
		for(String path: url.split("/")) {
			if(path.startsWith("{") && path.endsWith("}")) {
				pathArgs.add(path.substring(1, path.length() - 1));
			}
		}
	}
	
	public String getBody() {
		return body;
	}

	public String getRequestStr() {
		return requestStr;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public String getMethod() {
		return method;
	}

	protected String signature(PrivateKey privateKey) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append(method).append("\n");
		if(GET.equals(method)) {
			requestStr = buildQueryStr();
		}else {
			requestStr = url;
		}
		sb.append(requestStr).append("\n");
		timestamp = System.currentTimeMillis()/1000;
		sb.append(timestamp).append("\n");
		nonceStr = randomString(32);
		sb.append(nonceStr).append("\n");
		if(GET.equals(method)) {
			body = "";
		}else {
			body = buildBody();
		}
		sb.append(body).append("\n");
		String message = sb.toString();
		String signature = sign(privateKey, message.getBytes("utf-8"));
		return signature;
	}
	
	private String buildQueryStr() throws Exception{
		StringBuffer sb = new StringBuffer();
		Class<?> cls = this.getClass();
		String path = url;
		for(Field field : cls.getDeclaredFields()) {
			String name = field.getName();
			field.setAccessible(true);
			Object value = field.get(this);
			if(value == null) {
				continue;
			}
			if(pathArgs.contains(name)) {
				path = path.replace("{" + name + "}", value.toString());
				continue;
			}
			if(sb.length() > 0) {
				sb.append("&");
			}
			sb.append(name).append("=").append(value);
		}
		return path + "?" + sb.toString();
	}

	private String sign(PrivateKey privateKey, byte[] message)throws Exception {
	    Signature sign = Signature.getInstance("SHA256withRSA");
	    sign.initSign(privateKey);
	    sign.update(message);

	    return Base64.getEncoder().encodeToString(sign.sign());
	}
	
	private String buildBody()throws Exception {
		ObjectMapper om = new ObjectMapper();
		Object value = buildObject(this);
		String json = om.writeValueAsString(value);
		return json;
	}
	
	private Object buildObject(Object root)throws Exception {
		if(root instanceof String) {
			return root;
		}
		Class<?> cls = root.getClass();
		if(cls.isPrimitive()) {
			return root;
		}
		if(cls.isArray()) {
			Object[] arrays = (Object[])root;
			Object[] values = new Object[arrays.length];
			for(int i = 0 ; i < arrays.length; i++) {
				values[i] = buildObject(arrays[i]);
			}
			return values;
		}
		Map<String, Object> bean = new HashMap<>();
		for(Field field : cls.getDeclaredFields()) {
			String name = field.getName();
			field.setAccessible(true);
			Object value = field.get(root);
			if(value == null) {
				continue;
			}
			value = buildObject(value);
			bean.put(name, value);
		}
		return bean;
	}
	
	public static String randomString(int len){
		return RandomStringUtils.random(len, chars);
	}

	private final static char[] chars = new char[]{
		'a','b','c','d','e','f','g', 
		'h','i','j','k','l','m','n', 
		'o','p','q','r','s','t', 
		'u','v','w','x','y','z',
		'A','B','C','D','E','F','G', 
		'H','I','J','K','L','M','N', 
		'O','P','Q','R','S','T', 
		'U','V','W','X','Y','Z',
		'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

}

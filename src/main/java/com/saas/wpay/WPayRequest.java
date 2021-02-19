package com.saas.wpay;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


import com.fasterxml.jackson.databind.ObjectMapper;


public abstract class WPayRequest implements Const {
	
	private String url;
	private String method;
	
	private Set<String> pathArgs = new HashSet<>();
	
	protected WPayRequest(String url, String method) {
		this.url = "/v3/" + url;
		for(String path: url.split("/")) {
			if(path.startsWith("{") && path.endsWith("}")) {
				pathArgs.add(path.substring(1, path.length() - 1));
			}
		}
	}

	public String getMethod() {
		return method;
	}
	
	public String buildRequestStr() throws Exception{
		if(POST.equals(method)) {
			return url;
		}
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
			value = convert(value);
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
	
	public String buildBody()throws Exception {
		if(GET.equals(method)) {
			return "";
		}
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
		if(root instanceof Date || root instanceof Enum) {
			return convert(root);
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

	private Object convert(Object value) {
		if(value instanceof Enum) {
			return ((Enum<?>)value).name();
		}else if(value instanceof Date) {
			DateFormat df =new SimpleDateFormat("YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE");
			return df.format((Date)value);
		}
		return value;
	}

}

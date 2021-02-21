package com.saas.training;

import java.util.Enumeration;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.saas.exception.DataValidateException;
import com.saas.pub.MD5;
import com.zpsenior.graphql4j.spring.GraphQLInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityInterceptor extends GraphQLInterceptor {
	
	private static final String REQUEST_SIGN = "sign";
	
	private static final String REQUEST_NONCE = "nonce";
	
	private static final String REQUEST_TIMESTAMP = "timestamp";
	
	private static final String REQUEST_USERID = "userId";
	
	private String getSecretKey(HttpServletRequest request) {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		return request.getHeader(REQUEST_USERID);
	}

	protected void validateParam(HttpServletRequest request)throws Exception{
		String sign = request.getHeader(REQUEST_SIGN);
		String nonce = request.getHeader(REQUEST_NONCE);
		String timestamp = request.getHeader(REQUEST_TIMESTAMP);
		String secretKey = getSecretKey(request);
		TreeMap<String, String> values = new TreeMap<>();
		Enumeration<String> enumer = request.getParameterNames();
		while(enumer.hasMoreElements()) {
			String key = enumer.nextElement();
			String value = request.getParameter(key);
			values.put(key, value);
		}
		String requestStr = MD5.formatQueryString(values);
		String checkSign = MD5.encode(requestStr + "\n" + nonce + "\n" + timestamp + "\n" + secretKey);
		if(!checkSign.equalsIgnoreCase(sign)){
			log.error("requestStr : " + requestStr);
			log.error("sign : " + sign);
			log.error("checkSign : " + checkSign);
			throw new DataValidateException("diff.sign.token");
		}
	}

	protected void validateBody(HttpServletRequest request, String body) throws Exception{
		String sign = request.getHeader(REQUEST_SIGN);
		String nonce = request.getHeader(REQUEST_NONCE);
		String timestamp = request.getHeader(REQUEST_TIMESTAMP);
		String secretKey = getSecretKey(request);
		String checkSign = MD5.encode(body + "\n" + nonce + "\n" + timestamp + "\n" + secretKey);
		if(!checkSign.equalsIgnoreCase(sign)){
			log.error("body : " + body);
			log.error("sign : " + sign);
			log.error("checkSign : " + checkSign);
			throw new DataValidateException("diff.sign.token");
		}
	}

}

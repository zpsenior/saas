package com.saas.training;

import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import com.saas.exception.DataValidateException;
import com.saas.pub.MD5;
import com.zpsenior.graphql4j.ParamFinder;
import com.zpsenior.graphql4j.spring.GraphQLInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityInterceptor extends GraphQLInterceptor {
	
	private static final String REQUEST_SIGN = "sign";
	
	private static final String SECRET_KEY = "secretKey";

	protected void validate(HttpServletRequest request, ParamFinder<?> finder)throws Exception{
		String sign = request.getHeader(REQUEST_SIGN);
		String secretKey = (String)request.getAttribute(SECRET_KEY);
		TreeMap<String, String> values = new TreeMap<>();
		for(String key : finder.getParamNames()) {
			values.put(key, finder.getParamValue(key).toString());
		}
		String requestStr = MD5.formatQueryString(values, secretKey);
		String checkSign = MD5.encode(requestStr);
		if(!checkSign.equalsIgnoreCase(sign)){
			log.error("requestStr : " + requestStr);
			log.error("sign : " + sign);
			log.error("checkSign : " + checkSign);
			throw new DataValidateException("diff.sign.token");
		}
	}

	protected void validate(HttpServletRequest request, String entryName) throws Exception{
		String secretKey = "";
		request.setAttribute(SECRET_KEY, secretKey);
	}

}

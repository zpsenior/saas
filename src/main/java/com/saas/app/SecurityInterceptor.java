package com.saas.app;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import com.saas.auth.session.Session;

//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saas.exception.DataValidateException;
import com.saas.pub.MD5;
import com.saas.pub.service.CacheService;
import com.zpsenior.graphql4j.ParamFinder;
import com.zpsenior.graphql4j.spring.GraphQLInterceptor;
import com.zpsenior.graphql4j.spring.JsonParamFinder;
import com.zpsenior.graphql4j.spring.StringParamFinder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityInterceptor extends GraphQLInterceptor {
	
	private static final String REQUEST_SESSIONID = "sessionId";
	
	private static final String REQUEST_SIGN = "sign";
	
	private static final String REQUEST_NONCE = "nonce";
	
	private static final String REQUEST_TIMESTAMP = "timestamp";
	
	private static final String REQUEST_USERID = "userId";
	
	private CacheService cacheService;
	
	private Session session;
	
	public SecurityInterceptor(CacheService cacheService, Session session) {
		this.cacheService = cacheService;
		this.session = session;
	}
	
	private String getSecretKey(HttpServletRequest request) {
		//WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		return request.getHeader(REQUEST_USERID);
	}

	private String readContent(InputStream inputStream)throws Exception {
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer)) != -1) {
		    result.write(buffer, 0, length);
		}
		return result.toString("UTF-8");
	}

	@Override
	protected void checkPermission(HttpServletRequest request, String entryName) throws Exception {
		String sessionId = request.getHeader(REQUEST_SESSIONID);
		if(sessionId == null) {
			
		}
		String key = session.getPrefixKey() + Thread.currentThread().getId();
		cacheService.setProperty(key, sessionId, 5 * 60);
	}
	
	protected ParamFinder<?> buildParamFinder(HttpServletRequest request)throws Exception{
		ParamFinder<?> finder;
		String contentType = request.getContentType();
		if(contentType == null) {
			return null;
		}
		if(contentType.endsWith("/json")) {
			String content = readContent(request.getInputStream());
			validateContent(request, content);
			JsonNode root = (new ObjectMapper()).readTree(content);
			finder = new JsonParamFinder(root);
		}else{
			Map<String, String> params = validateParam(request);
			finder = new StringParamFinder(params);
		}
		return finder;
	}

	protected Map<String, String> validateParam(HttpServletRequest request)throws Exception{
		String sign = request.getHeader(REQUEST_SIGN);
		String nonce = request.getHeader(REQUEST_NONCE);
		String timestamp = request.getHeader(REQUEST_TIMESTAMP);
		String secretKey = getSecretKey(request);
		TreeMap<String, String> values = new TreeMap<>();
		Enumeration<String> enumer = request.getParameterNames();
		while(enumer.hasMoreElements()) {
			String key = enumer.nextElement();
			String[] value = request.getParameterValues(key);
			if(value.length > 1) {
				values.put(key, Arrays.toString(value));
			}else {
				values.put(key, value[0]);
			}
		}
		String requestStr = MD5.formatQueryString(values);
		String checkSign = MD5.encode(requestStr + "\n" + nonce + "\n" + timestamp + "\n" + secretKey);
		if(!checkSign.equalsIgnoreCase(sign)){
			log.error("requestStr : " + requestStr);
			log.error("sign : " + sign);
			log.error("checkSign : " + checkSign);
			throw new DataValidateException("diff.sign.token");
		}
		return values;
	}

	protected void validateContent(HttpServletRequest request, String body) throws Exception{
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

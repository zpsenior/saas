package com.saas.auth.wechat;

import java.net.URLEncoder;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

public class WechatAuth {
	
	@Data
	public class AuthToken{
		private String openid;
		private String unionid;
		private String sessionKey;
		private String accessToken;
		private String refreshToken;
		private int expiresIn;
	}
	
	private final static String CODE2SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session";
	private final static String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
	private final static String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
	private final static String GET_UNION_ID_URL = "https://api.weixin.qq.com/wxa/getpaidunionid";
	
	private String appId;
	
	private String secret;
	
	public WechatAuth(String appId, String secret) {
		this.appId = appId;
		this.secret = secret;
	}

	public JsonNode get(String url) throws Exception{
		
		CloseableHttpClient client = HttpClientBuilder.create().build();
		
		HttpGet httpGet = new HttpGet(url);
		
		CloseableHttpResponse response = client.execute(httpGet);
		
		int statusCode = response.getStatusLine().getStatusCode() ;
		if (statusCode!= 200) {
			throw new RuntimeException("request error, code:" + statusCode);
		}

		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode root =mapper.readTree(response.getEntity().getContent());
		
		return root;
	}
	
	public String buildQrCodeRequest(String redirectUrl, String state) throws Exception{
		
		StringBuilder sb = new StringBuilder();
		sb.append("appid=").append(appId);
		sb.append("&redirect_url=").append(URLEncoder.encode(redirectUrl, "utf-8"));
		sb.append("&response_type=code");
		sb.append("&scope=snsapi_login");
		sb.append("&state=").append(URLEncoder.encode(state, "utf-8"));
		
		return sb.toString();
	}

	public AuthToken code2Session(String code) throws Exception{
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(CODE2SESSION_URL).append("?");
		sb.append("appid=").append(appId);
		sb.append("&secret=").append(secret);
		sb.append("&js_code=").append(code);
		sb.append("&grant_type=authorization_code");
		
		JsonNode root = get(sb.toString());
		
		int errorCode = root.get("errcode").asInt();
		
		String errorMsg = root.get("errmsg").asText();
		
		if(errorCode != 0) {
			throw new RuntimeException("code2Session error, code:" + errorCode + ", msg:" + errorMsg);
		}
		
		AuthToken token = new AuthToken();
		
		token.openid = root.get("openid").asText();
		token.unionid = root.get("unionid").asText();
		token.sessionKey = root.get("session_key").asText();
		
		return token;
		
	}

	public AuthToken getAccessToken(String code) throws Exception{
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(GET_ACCESS_TOKEN_URL).append("?");
		sb.append("appid=").append(appId);
		sb.append("&secret=").append(secret);
		sb.append("&code=").append(code);
		sb.append("&grant_type=authorization_code");
		
		JsonNode root = get(sb.toString());

		AuthToken token = new AuthToken();
		
		token.openid = root.get("openid").asText();
		token.accessToken = root.get("access_token").asText();
		token.refreshToken = root.get("refresh_token").asText();
		token.expiresIn = root.get("expires_in").asInt();
		
		return token;
	}
	
	public void refreshAccessToken(String refreshToken) throws Exception{
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(REFRESH_TOKEN_URL).append("?");
		sb.append("appid=").append(appId);
		sb.append("&grant_type=refresh_token");
		sb.append("&refresh_token=").append(refreshToken);
		
		JsonNode root = get(sb.toString());
		
		System.out.println(root);
		
	}
	
	public boolean validateAccessToken(String openid, String accessToken) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(REFRESH_TOKEN_URL).append("?");
		sb.append("openid=").append(openid);
		sb.append("&access_token=").append(accessToken);
		
		JsonNode root = get(sb.toString());
		
		int errorCode = root.get("errcode").asInt();
        return errorCode == 0;
		
	}
	
	public String getUnionid(String openid, String accessToken) throws Exception {

		StringBuilder sb = new StringBuilder();
		
		sb.append(GET_UNION_ID_URL).append("?");
		sb.append("openid=").append(openid);
		sb.append("&access_token=").append(accessToken);
		
		JsonNode root = get(sb.toString());
		
		int errorCode = root.get("errcode").asInt();
		
		if(errorCode != 0) {
			throw new RuntimeException("getUnionid error, code:" + errorCode);
		}
		return root.get("unionid").asText();
	}
}

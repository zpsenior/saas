package com.saas.auth.session;

import com.saas.pub.service.CacheService;

import lombok.Data;

@Data
public abstract class Session {

	private String openid;

	private String unionid;

	private String sessionKey;

	private String accessToken;

	private String refreshToken;

	private int expiresIn;
	
	protected Session(String prefixKey, String prefix) {
		this.prefixKey = prefixKey;
		this.prefix = prefix;
	}
	
	private String prefixKey;
	
	private String prefix;
	
	public final void save(CacheService cacheService) {
		String key = getPrefix() + getSessionId();
		cacheService.setProperty(key, this, getExpiresIn() * 1000);
	}
	
	public abstract String getSessionId();
	

	@SuppressWarnings("unchecked")
	public static <T extends Session> T getInstance(CacheService cacheService, T session) {
		long threadId = Thread.currentThread().getId();
		String sessionKey = (String)cacheService.getProperty(session.getPrefixKey() + threadId);
		Object obj = cacheService.getProperty(session.getPrefix() + sessionKey);
		if(obj == null) {
			throw new RuntimeException("login expire time!");
		}
		return (T)obj;
	}
}

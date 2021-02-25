package com.saas.pub.service;

public interface CacheService {

	public void setProperty(String key, Object value, int expireTime) ;
	
	public Object getProperty(String key) ;
}
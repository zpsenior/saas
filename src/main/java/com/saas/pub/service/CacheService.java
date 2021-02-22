package com.saas.pub.service;

public interface CacheService {

	public void setProperty(String key, String value, int expireTime) ;
	
	public String getProperty(String key) ;
}
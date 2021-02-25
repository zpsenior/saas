package com.saas.redis;

import org.springframework.stereotype.Service;

import com.saas.pub.service.CacheService;

@Service
public class RedisCacheService implements CacheService {

	@Override
	public void setProperty(String key, Object value, int expireTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getProperty(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}

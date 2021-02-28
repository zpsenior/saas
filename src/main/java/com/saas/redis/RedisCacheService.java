package com.saas.redis;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.saas.pub.service.CacheService;

@Service
public class RedisCacheService implements CacheService {
	
	@Autowired(required=false)
	private RedisTemplate<String, Object> template;

	@Override
	public void setProperty(String key, Object value, int expireTime) {
		Duration timeout = Duration.ofSeconds(expireTime);
		template.opsForValue().set(key, value, timeout);
	}

	@Override
	public Object getProperty(String key) {
		return template.opsForValue().get(key);
	}

}

package com.saas.pub.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeService {
	
	@Autowired
	private SMSService smsService;
	
	@Autowired(required=false)
	private CacheService cacheService;
	
	private final static char[] chars = new char[]{
			'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
		
	public final static String CHECK_CODE_KEY = "check.code";
		
	private String generateCheckCode(int len) {
		String checkcode = RandomStringUtils.random(len, chars);
		return checkcode;
	}

	public void sendCheckCode(String mobileno, String templateCode, int expireTime) throws Exception{
		String checkCode = generateCheckCode(6);
		if(cacheService != null) {
			cacheService.setProperty(mobileno + "_" + templateCode, checkCode, expireTime);
		}
		sendMessage(mobileno, templateCode, checkCode);
	}

	public boolean verifyCheckCode(String mobileno, String templateCode, String checkCode) {
		if(cacheService == null) {
			return true;
		}
		String code = (String)cacheService.getProperty(mobileno + "_" + templateCode);
		return code != null && checkCode != null && checkCode.equals(code);
	}

	private void sendMessage(String mobileno, String templateCode, String checkCode) throws Exception{
		if(smsService == null) {
			System.out.println("mobileno:" + mobileno + ", checkCode:" + checkCode);
			return;
		}
		Map<String, String> params = new HashMap<>();
		params.put("checkCode", checkCode);
		smsService.sendMessage(mobileno, templateCode, params);
	}
}

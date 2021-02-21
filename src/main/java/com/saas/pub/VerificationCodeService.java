package com.saas.pub;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeService {
	
	@Value("${saas.register.templateId}")
	private String registerTemplateId;
	
	private final static char[] chars = new char[]{
			'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
		
	public final static String CHECK_CODE_KEY = "check.code";
		
	private String generateCheckCode(int len) {
		String checkcode = RandomStringUtils.random(len, chars);
		return checkcode;
	}

	public void sendCheckCode(String mobileno, String templateId, int expireTime) {
		String checkCode = generateCheckCode(6);
		setCache(mobileno + "_" + templateId, checkCode, expireTime);
		sendMessage(mobileno, templateId, checkCode);
	}

	public boolean verifyCheckCode(String mobileno, String templateId, String checkCode) {
		String code = getCache(mobileno + "_" + templateId);
		return code != null && checkCode != null && checkCode.equals(code);
	}

	private void sendMessage(String mobileno, String templateId, String checkCode) {
		// TODO Auto-generated method stub
		
	}

	private void setCache(String key, String checkCode, int expireTime) {
		// TODO Auto-generated method stub
		
	}

	private String getCache(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}

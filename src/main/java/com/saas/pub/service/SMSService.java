package com.saas.pub.service;

import java.util.Map;

public interface SMSService {

	public void sendMessage(String mobileno, String templateCode, Map<String, String> params)throws Exception;
}

package com.saas.aliyun;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.aliyun.teaopenapi.models.*;
import com.saas.pub.service.SMSService;

@Service
public class AliSmsService implements SMSService {

	@Value("${saas.sms.AccessKeyId}")
	private String smsAccessKeyId;

	@Value("${saas.sms.AccessKeySecret}")
	private String smsAccessKeySecret;
	
	@Value("${saas.sms.SignName}")
	private String signName;

	@Override
	public void sendMessage(String mobileno, String templateCode, Map<String, String> params) throws Exception {
		String templateParam = toJson(params);
		Config config = new Config();
        config.accessKeyId = smsAccessKeyId;
        config.accessKeySecret = smsAccessKeySecret;
        config.endpoint = "ecs.aliyuncs.com";
        Client client = new Client(config);
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(mobileno);
        request.setTemplateCode(templateCode);
        request.setTemplateParam(templateParam);
        request.setSignName(signName);
        SendSmsResponse response = client.sendSms(request);
        SendSmsResponseBody body = response.getBody();
        if(!"OK".equalsIgnoreCase(body.getCode())) {
        	throw new RuntimeException("send message error: " + body.getCode() + " - "+ body.getMessage());
        }
	}

	private String toJson(Map<String, String> params) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for(String key : params.keySet()) {
			String value = params.get(key);
			if(sb.length() > 1) {
				sb.append(",");
			}
			sb.append("\"").append(key).append("\"");
			sb.append(":");
			sb.append("\"").append(value).append("\"");
		}
		sb.append("}");
		return sb.toString();
	}

}

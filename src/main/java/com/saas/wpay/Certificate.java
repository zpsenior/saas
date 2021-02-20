package com.saas.wpay;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.saas.pub.MD5;
import com.saas.pub.SignUtils;

@Component
public class Certificate {

	@Value("${wpay.publicKeyFile}")
	private String publicKeyFile;

	@Value("${wpay.privateKeyFile}")
	private String privateKeyFile;

	@Value("${wpay.secretKeyStr}")
	private String secretKeyStr;

	private PrivateKey privateKey;

	private PublicKey publicKey;

	private SecretKeySpec secretKey;

	private boolean inited = false;

	//@PostConstruct
	public void init() throws Exception {
		if (inited) {
			return;
		}
		privateKey = SignUtils.getPrivateKeyFromPem(privateKeyFile);
		publicKey = SignUtils.getPublicKey(publicKeyFile);
		secretKey = new SecretKeySpec(MD5.encode(secretKeyStr, "UTF-8").toLowerCase().getBytes(), "AES");
		inited = true;
	}

	public String doSign(String method, String requestStr, String body, String nonceStr, long timestamp)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(method).append("\n");
		sb.append(requestStr).append("\n");
		sb.append(timestamp).append("\n");
		sb.append(nonceStr).append("\n");
		sb.append(body).append("\n");
		String message = sb.toString();
		String signature = SignUtils.sign(privateKey, message);
		return signature;
	}

	public boolean doVerify(long timestamp, String nonceStr, String body, String signature) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(timestamp).append("\n");
		sb.append(nonceStr).append("\n");
		sb.append(body).append("\n");
		String message = sb.toString();

		return SignUtils.verify(publicKey, message, signature);
	}

	public String decryptToString(byte[] associatedData, byte[] nonce, String ciphertext) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		
		GCMParameterSpec spec = new GCMParameterSpec(128, nonce);

		cipher.init(Cipher.DECRYPT_MODE, secretKey, spec);
		cipher.updateAAD(associatedData);

		return new String(cipher.doFinal(Base64.getDecoder().decode(ciphertext)), "UTF-8");
	}

	public String sign4JSAPI(String appid, long timestamp, String nonceStr, String body) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append(appid).append("\n");
		sb.append(timestamp).append("\n");
		sb.append(nonceStr).append("\n");
		sb.append(body).append("\n");
		String message = sb.toString();
		String signature = SignUtils.sign(privateKey, message);
		return signature;
	}
}

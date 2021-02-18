package com.saas.pub;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Enumeration;

import org.apache.commons.lang.RandomStringUtils;

public class SignUtils {
	
	public static PrivateKey getPrivateKeyFromPem(String filename) throws Exception {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
	    BufferedReader br = new BufferedReader(new InputStreamReader(is));
	    br.readLine();//skip first line
	    StringBuffer sb = new StringBuffer();
	    String line = br.readLine();
	    while (line.charAt(0) != '-') {
	        sb.append(line).append("\r");
	        line = br.readLine();
	    }
	    byte[] content = Base64.getDecoder().decode(sb.toString());

	    // 生成私匙  
	    KeyFactory kf = KeyFactory.getInstance("RSA");
	    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(content);
	    PrivateKey key = kf.generatePrivate(keySpec);
	    return key;
	}
	
	public static PrivateKey getPrivateKey(String filename, String privateKeyPwd) throws Exception {
		KeyStore store = KeyStore.getInstance("JKS");
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
		try{
			store.load(is, privateKeyPwd.toCharArray());
		}finally {
			is.close();
		}
		Enumeration<?> en = store.aliases();
        String pName = null;
        while (en.hasMoreElements()) {
            String n = (String) en.nextElement();
            if (store.isKeyEntry(n)) {
                pName = n;
            }
        }
        PrivateKey key = (PrivateKey) store.getKey(pName, privateKeyPwd.toCharArray());
        return key;
    }
	
	public static PublicKey getPublicKey(String filename) throws Exception{
		PublicKey pk;
		X509Certificate cert;
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
		try{
			CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
	        cert = (X509Certificate)certificatefactory.generateCertificate(is);
		}finally {
			is.close();
		}
        pk = cert.getPublicKey();
		return pk;
	}

	public static byte[] readBytes(InputStream is) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		byte[] buff = new byte[1024];
		while (true){
			int len = is.read(buff);
			if(len < 0) {
				break;
			}
			out.write(buff, 0, len);
		}
		byte[] result = out.toByteArray();
		return result;
	}

	public static String sign(PrivateKey privateKey, String message)throws Exception {
	    Signature sign = Signature.getInstance("SHA256withRSA");
	    sign.initSign(privateKey);
	    sign.update(message.getBytes("utf-8"));

	    return Base64.getEncoder().encodeToString(sign.sign());
	}
	
	public static boolean verify(PublicKey publicKey, String message, String signature)throws Exception {

		Signature sign = Signature.getInstance("SHA256withRSA");
	    sign.initVerify(publicKey);
	    sign.update(message.getBytes("UTF-8"));
	    return sign.verify(Base64.getDecoder().decode(signature.getBytes("UTF-8")));
	}

	
	public static String randomString(int len){
		return RandomStringUtils.random(len, chars);
	}

	private final static char[] chars = new char[]{
		'a','b','c','d','e','f','g', 
		'h','i','j','k','l','m','n', 
		'o','p','q','r','s','t', 
		'u','v','w','x','y','z',
		'A','B','C','D','E','F','G', 
		'H','I','J','K','L','M','N', 
		'O','P','Q','R','S','T', 
		'U','V','W','X','Y','Z',
		'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

}

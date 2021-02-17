package com.saas.pub;

import java.security.MessageDigest;
import java.util.TreeMap;

public class MD5 {
	
	public final static String encode(String s)throws Exception{
		return encode(s, null);
	}
	
	public final static String encode(String s, String charset)throws Exception {
        byte[] btInput;
        if(charset != null) {
        	btInput = s.getBytes(charset);
        }else {
        	btInput = s.getBytes();
        }
        return encode(btInput);
	}
	
	private final static char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
	
    public final static String encode(byte[] btInput)throws Exception{       

        MessageDigest mdInst = MessageDigest.getInstance("MD5");

        mdInst.update(btInput);

        byte[] md = mdInst.digest();

        int j = md.length;
        char str[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str);
    }
    
	public static String formatQueryString(TreeMap<String, String> map, String hashCode)throws Exception {
		StringBuffer sb = new StringBuffer();
		for(String key : map.keySet()){
			String value = map.get(key);
			sb.append(key);
			sb.append(value);
			sb.append("&");
		}
		hashCode = MD5.encode(hashCode).toLowerCase();
		sb.append(hashCode);
		String token = sb.toString();
		token = new String(token.getBytes(), "UTF-8");
		return token;
	}
}

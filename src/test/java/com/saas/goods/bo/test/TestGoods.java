package com.saas.goods.bo.test;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class TestGoods extends TestBase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void testCreateGoods() throws Exception{
		Map<String, String> params = new HashMap<>();
		
		params.put("", "");
		
		get("/staff/createGoods", params);
	}
	
	public static void testUpdateGoods() throws Exception{
		ObjectNode params = (new ObjectMapper()).createObjectNode();
		
		params.put("", "");
		
		post("/staff/createGoods", params);
	}

}

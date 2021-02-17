package com.saas.wpay.ecommerce;

import com.saas.wpay.WPayRequest;

public class BuildOrder extends WPayRequest {
	
	protected BuildOrder() {
		super("pay/partner/transactions/jsapi", POST);
	}

	private String sp_appid;
	
	private String sp_mchid;
	
	private String sub_appid;
	
	private String sub_mchid;
	
	private String description;
	
	private String out_trade_no;
	
	private String time_expire;
	
	private String attach;
	
	private String notify_url;
}

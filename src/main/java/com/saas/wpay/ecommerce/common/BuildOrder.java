package com.saas.wpay.ecommerce.common;

import com.saas.wpay.WPayRequest;
import com.saas.wpay.ecommerce.po.SettleInfo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class BuildOrder extends WPayRequest {
	
	@Data
	public class Amount{
		
		private long total;
		private String currency;
	}
	
	@Data
	public class Payer{
		private String sp_openid;
		private String sub_openid;
	}
	
	public BuildOrder() {
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
	
	private String goods_tag;

	private SettleInfo settle_info;
	
	private Amount amount;
	
	private Payer payer;
}

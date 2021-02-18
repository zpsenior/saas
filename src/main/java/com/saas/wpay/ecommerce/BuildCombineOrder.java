package com.saas.wpay.ecommerce;

import java.util.Date;

import com.saas.wpay.WPayRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class BuildCombineOrder extends WPayRequest{
	
	@Data
	public class PayInfo{
		private String openid;
	}

	protected BuildCombineOrder() {
		super("combine-transactions/jsapi", POST);
	}
	
	private String combine_appid;
	
	private String combine_mchid;
	
	private String combine_out_trade_no;
	
	private SubOrder[] sub_orders;
	
	private PayInfo combine_payer_info;
	
	private Date time_start;
	
	private Date time_expire;
	
	private String notify_url;

}

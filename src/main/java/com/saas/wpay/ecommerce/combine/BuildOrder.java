package com.saas.wpay.ecommerce.combine;

import java.util.Date;

import com.saas.wpay.WPayRequest;
import com.saas.wpay.ecommerce.po.SubOrder;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class BuildOrder extends WPayRequest{
	
	@Data
	public class PayInfo{
		private String openid;
	}

	public BuildOrder() {
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

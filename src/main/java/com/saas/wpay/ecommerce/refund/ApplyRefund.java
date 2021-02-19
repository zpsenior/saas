package com.saas.wpay.ecommerce.refund;

import com.saas.wpay.WPayRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class ApplyRefund extends WPayRequest {
	
	@Data
	public class Amount{
		private int refund;
		private int total;
		private int currency;
	}

	public ApplyRefund() {
		super("ecommerce/refunds/apply", POST);
	}
	
	private String sub_mchid;   
	
	private String sp_appid;	
	
	private String sub_appid;	
	
	private String transaction_id;
	
	private String out_trade_no;
	
	private String out_refund_no;	
	
	private String reason;     
	
	private Amount amount;  
	
	private String notify_url;    


}

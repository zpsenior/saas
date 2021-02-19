package com.saas.wpay.ecommerce.profitsharing;

import com.saas.wpay.WPayRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class BuildOrder extends WPayRequest {
	
	@Data
	public class Receiver{
		private String type;
		private String receiver_account;
		private int amount;
		private String description;
		private String receiver_name;
	}
	
	public BuildOrder() {
		super("ecommerce/profitsharing/orders", POST);
	}

	private String appid;
	
	private String sub_mchid;
	
	private String transaction_id;
	
	private String out_order_no;
	
	private Receiver[] receivers;
	
	private boolean finish = false;

}

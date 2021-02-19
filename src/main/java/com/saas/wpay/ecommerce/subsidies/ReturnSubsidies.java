package com.saas.wpay.ecommerce.subsidies;

import com.saas.wpay.WPayRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class ReturnSubsidies extends WPayRequest {

	public ReturnSubsidies() {
		super("ecommerce/subsidies/return", POST);
	}

	
	private String sub_mchid;
	
	private String out_order_no;
	
	private String transaction_id;
	
	private String refund_id;
	
	private int amount;
	
	private String description;
}

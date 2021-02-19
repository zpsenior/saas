package com.saas.wpay.ecommerce.profitsharing;

import com.saas.wpay.WPayRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class QueryOrder extends WPayRequest {

	public QueryOrder() {
		super("ecommerce/profitsharing/orders", GET);
	}
	
	private String sub_mchid;
	
	private String transaction_id;
	
	private String out_order_no;

}

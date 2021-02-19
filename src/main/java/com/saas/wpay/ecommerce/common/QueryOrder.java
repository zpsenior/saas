package com.saas.wpay.ecommerce.common;

import com.saas.wpay.WPayRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class QueryOrder extends WPayRequest{
	
	public QueryOrder() {
		super("pay/partner/transactions/id/{transaction_id}", GET);
	}

	private String sp_mchid;
	
	private String sub_mchid;
	
	private String transaction_id;
	
	private String out_trade_no;

}

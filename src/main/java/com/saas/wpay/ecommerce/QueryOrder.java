package com.saas.wpay.ecommerce;

import com.saas.wpay.WPayRequest;

public class QueryOrder extends WPayRequest{
	
	protected QueryOrder() {
		super("pay/partner/transactions/id/{transaction_id}", GET);
	}

	private String sp_mchid;
	
	private String sub_mchid;
	
	private String transaction_id;
	
	private String out_trade_no;

}

package com.saas.wpay.ecommerce;

import com.saas.wpay.WPayRequest;

public class CloseOrder extends WPayRequest {
	
	protected CloseOrder() {
		super("pay/partner/transactions/out-trade-no/{out_trade_no}/close", POST);
	}

	private String sp_mchid;
	
	private String sub_mchid;
	
	private String out_trade_no;

}

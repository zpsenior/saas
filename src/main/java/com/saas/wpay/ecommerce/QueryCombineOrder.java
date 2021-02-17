package com.saas.wpay.ecommerce;

import com.saas.wpay.WPayRequest;

public class QueryCombineOrder extends WPayRequest  {

	protected QueryCombineOrder() {
		super("combine-transactions/out-trade-no/{combine_out_trade_no}", GET);
	}

}

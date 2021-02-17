package com.saas.wpay.ecommerce;

import com.saas.wpay.WPayRequest;

public class CloseCombineOrder extends WPayRequest  {

	protected CloseCombineOrder() {
		super("combine-transactions/out-trade-no/{combine_out_trade_no}/close", POST);
	}

}

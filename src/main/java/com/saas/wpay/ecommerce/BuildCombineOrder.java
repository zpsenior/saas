package com.saas.wpay.ecommerce;

import com.saas.wpay.WPayRequest;


public class BuildCombineOrder extends WPayRequest{

	protected BuildCombineOrder() {
		super("combine-transactions/jsapi", POST);
	}

}

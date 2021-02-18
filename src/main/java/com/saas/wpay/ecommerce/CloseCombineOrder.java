package com.saas.wpay.ecommerce;

import com.saas.wpay.WPayRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class CloseCombineOrder extends WPayRequest  {

	protected CloseCombineOrder() {
		super("combine-transactions/out-trade-no/{combine_out_trade_no}/close", POST);
	}
	
	private String combine_appid;
	
	private String combine_out_trade_no;
	
	private SubOrder[] sub_orders;

}

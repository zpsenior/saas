package com.saas.wpay.ecommerce.combine;

import com.saas.wpay.WPayRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class QueryOrder extends WPayRequest  {

	public QueryOrder() {
		super("combine-transactions/out-trade-no/{combine_out_trade_no}", GET);
	}

	
	private String combine_out_trade_no;
}

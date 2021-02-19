package com.saas.wpay.ecommerce.common;

import com.saas.wpay.WPayRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class CloseOrder extends WPayRequest {
	
	public CloseOrder() {
		super("pay/partner/transactions/out-trade-no/{out_trade_no}/close", POST);
	}

	private String sp_mchid;
	
	private String sub_mchid;
	
	private String out_trade_no;

}

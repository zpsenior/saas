package com.saas.wpay.ecommerce.combine;

import com.saas.wpay.WPayRequest;
import com.saas.wpay.ecommerce.po.SubOrder;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class CloseOrder extends WPayRequest  {

	public CloseOrder() {
		super("combine-transactions/out-trade-no/{combine_out_trade_no}/close", POST);
	}
	
	private String combine_appid;
	
	private String combine_out_trade_no;
	
	private SubOrder[] sub_orders;

}

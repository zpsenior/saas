package com.saas.wpay.ecommerce.profitsharing;

import com.saas.wpay.WPayRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class QueryReturn extends WPayRequest {

	public QueryReturn() {
		super("ecommerce/profitsharing/returnorders", GET);
	}
	
	private String  sub_mchid;
	
	private String  order_id;
	
	private String  out_order_no;
	
	private String  out_return_no;


}

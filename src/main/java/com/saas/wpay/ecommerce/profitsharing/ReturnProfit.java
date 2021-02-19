package com.saas.wpay.ecommerce.profitsharing;

import com.saas.wpay.WPayRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class ReturnProfit extends WPayRequest {

	public ReturnProfit() {
		super("ecommerce/profitsharing/returnorders", POST);
	}
	
	private String  sub_mchid;
	
	private String  order_id;
	
	private String  out_order_no;
	
	private String  out_return_no;
	
	private String  return_mchid;
	
	private int     amount;
	
	private String  description;


}

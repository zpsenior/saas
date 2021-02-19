package com.saas.wpay.ecommerce.profitsharing;

import com.saas.wpay.WPayRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class FinishOrder extends WPayRequest {

	public FinishOrder() {
		super("ecommerce/profitsharing/finish-order", POST);
	}
	
	private String  sub_mchid;
	
	private String  transaction_id;
	
	private String  out_order_no;
	
	private String  description;


}

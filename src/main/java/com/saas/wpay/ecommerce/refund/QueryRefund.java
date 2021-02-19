package com.saas.wpay.ecommerce.refund;

import com.saas.wpay.WPayRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class QueryRefund extends WPayRequest {

	public QueryRefund() {
		super("ecommerce/refunds/id/{refund_id}", GET);
	}

	private String  refund_id;
	
	private String  sub_mchid;
}

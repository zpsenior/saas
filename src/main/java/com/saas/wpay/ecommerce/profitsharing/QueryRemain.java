package com.saas.wpay.ecommerce.profitsharing;

import com.saas.wpay.WPayRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class QueryRemain extends WPayRequest {

	public QueryRemain() {
		super("ecommerce/profitsharing/orders/{transaction_id}/amounts", GET);
	}

}

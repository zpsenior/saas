package com.saas.wpay.ecommerce.applyment;

import com.saas.wpay.WPayRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class QueryApply extends WPayRequest {

	public QueryApply() {
		super("ecommerce/applyments/{applyment_id}", GET);
	}

	private long applyment_id;
}

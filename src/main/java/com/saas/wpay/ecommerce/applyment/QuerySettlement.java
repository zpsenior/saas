package com.saas.wpay.ecommerce.applyment;

import com.saas.wpay.WPayRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class QuerySettlement extends WPayRequest {

	public QuerySettlement() {
		super("apply4sub/sub_merchants/{sub_mchid}/settlement", GET);
	}
	
	private String sub_mchid;

}

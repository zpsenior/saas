package com.saas.wpay.ecommerce.querybalance;

import com.saas.wpay.WPayRequest;
import com.saas.wpay.ecommerce.po.AccountType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class QueryMerchant extends WPayRequest {

	public QueryMerchant() {
		super("merchant/fund/balance/{account_type}", GET);
	}
	
	private AccountType account_type;

}

package com.saas.wpay.ecommerce.querybalance;

import com.saas.wpay.WPayRequest;
import com.saas.wpay.ecommerce.po.AccountType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class QuerySubMerchant extends WPayRequest {

	public QuerySubMerchant() {
		super("ecommerce/fund/balance/{sub_mchid}", GET);
	}
	
	private String sub_mchid;

	private AccountType account_type;

}

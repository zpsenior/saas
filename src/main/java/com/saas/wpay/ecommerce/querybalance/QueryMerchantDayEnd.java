package com.saas.wpay.ecommerce.querybalance;

import java.util.Date;

import com.saas.wpay.WPayRequest;
import com.saas.wpay.ecommerce.po.AccountType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class QueryMerchantDayEnd extends WPayRequest {

	public QueryMerchantDayEnd() {
		super("merchant/fund/dayendbalance/{account_type}", GET);
	}
	
	private AccountType account_type;
	
	private Date date;

}

package com.saas.payment.request;

import com.saas.pub.QueryParam;
import com.zpsenior.graphql4j.annotation.Input;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Input("QueryPaymentNotifyParam")
public class QueryPaymentNotifyParam extends QueryParam {

	private String tenantId;
	
	public QueryPaymentNotifyParam() {
		super("out_trade_no");
	}
}

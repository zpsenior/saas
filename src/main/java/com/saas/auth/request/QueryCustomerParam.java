package com.saas.auth.request;

import com.saas.pub.QueryParam;
import com.zpsenior.graphql4j.annotation.Input;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Input("QueryCustomerParam")
public class QueryCustomerParam extends QueryParam {

	private String tenantId;
	
	private String lognName;	
	
	public QueryCustomerParam() {
		super("customer_id");
	}
}

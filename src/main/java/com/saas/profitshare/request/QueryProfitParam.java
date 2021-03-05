package com.saas.profitshare.request;

import com.saas.pub.QueryParam;
import com.zpsenior.graphql4j.annotation.Input;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Input("QueryProfitParam")
public class QueryProfitParam extends QueryParam {

	public QueryProfitParam() {
		super("share_id");
	}
	
	private String tenantId;
	
	private long ownerId;

}

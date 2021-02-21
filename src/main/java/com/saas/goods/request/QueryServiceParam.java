package com.saas.goods.request;

import com.saas.pub.QueryParam;
import com.zpsenior.graphql4j.annotation.Input;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Input("QueryServiceParam")
public class QueryServiceParam extends QueryParam {

	private String tenantId;

	private long customerId;

	private long orderId;


	public QueryServiceParam() {
		super("service_id");
	}
}

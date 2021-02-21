package com.saas.auth.request;

import com.saas.pub.QueryParam;
import com.zpsenior.graphql4j.annotation.Input;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Input("QueryStaffParam")
public class QueryStaffParam extends QueryParam {

	private String tenantId;
	
	public QueryStaffParam() {
		super("staff_id");
	}
}

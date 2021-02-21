package com.saas.goods.request;

import com.saas.pub.QueryParam;
import com.zpsenior.graphql4j.annotation.Input;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Input("QueryLogisticsParam")
public class QueryLogisticsParam extends QueryParam  {
	
	private String tenant;

	public QueryLogisticsParam() {
		super("logistics_id");
	}
}

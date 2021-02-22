package com.saas.goods.request;

import java.util.Date;

import com.saas.pub.QueryParam;
import com.zpsenior.graphql4j.annotation.Input;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Input("QueryGoodsDailyParam")
public class QueryGoodsDailyParam extends QueryParam {

	public QueryGoodsDailyParam() {
		super("trade_date");
	}

	private String tenantId;

	private long goodsId;
	
	private Date beginDate;
	
	private Date endDate;
}

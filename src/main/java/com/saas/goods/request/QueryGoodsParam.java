package com.saas.goods.request;

import com.saas.pub.QueryParam;
import com.zpsenior.graphql4j.annotation.Input;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Input("QueryGoodsParam")
public class QueryGoodsParam extends QueryParam {

	private String goodsTitle;
	
	private String tenantId;
	
	private boolean showParent = false;

}

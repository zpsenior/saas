package com.saas.goods.bo;

import org.springframework.stereotype.Component;

import com.saas.goods.request.OrderParam;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("MutationGoodsOrder")
public class MutationGoodsOrder {

	@Field
	public boolean buildOrder(@Var("params") OrderParam order)throws Exception{
		return false;
	}
	
	@Field
	public boolean payOrder(@Var("params") OrderParam order)throws Exception{
		return false;
	}

}

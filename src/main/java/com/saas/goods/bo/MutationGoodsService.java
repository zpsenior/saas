package com.saas.goods.bo;

import org.springframework.stereotype.Component;

import com.saas.goods.request.ServiceParam;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("MutationGoodsService")
public class MutationGoodsService {

	@Field("create")
	public boolean createService(@Var("params") ServiceParam params)throws Exception{
		return false;
	}

}

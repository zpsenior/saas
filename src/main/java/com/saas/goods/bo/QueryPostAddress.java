package com.saas.goods.bo;

import java.util.List;

import org.springframework.stereotype.Component;

import com.saas.goods.request.PostAddressParam;
import com.saas.goods.vo.PostAddress;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("QueryPostAddress")
public class QueryPostAddress {

	@Field("addressList")
	public List<PostAddress> queryPostAddressList(@Var("params") PostAddressParam params)throws Exception{
		return null;
	}

	@Field("address")
	public PostAddress getPostAddress(@Var("params") PostAddressParam params)throws Exception{
		return null;
	}

}

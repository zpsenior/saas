package com.saas.goods.bo;

import org.springframework.stereotype.Component;

import com.saas.goods.request.PostAddressParam;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("MutationPostAddress")
public class MutationPostAddress {
	
	@Field("add")
	public boolean addPostAddress(@Var("address") PostAddressParam address)throws Exception{
		return false;
	}
	
	
	@Field("update")
	public boolean updatePostAddress(@Var("address") PostAddressParam address)throws Exception{
		return false;
	}
	
	@Field("remove")
	public boolean removePostAddress(@Var("address") PostAddressParam address)throws Exception{
		return false;
	}

}

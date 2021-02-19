package com.saas.goods.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.goods.dao.DAOPostAddress;
import com.saas.goods.request.PostAddressParam;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("MutationPostAddress")
public class MutationPostAddress {
	
	@Autowired
	private DAOPostAddress postAddress;
	
	@Field("add")
	public boolean addPostAddress(@Var("params") PostAddressParam params)throws Exception{
		postAddress.addPostAddress(params);
		return true;
	}
	
	
	@Field("update")
	public boolean updatePostAddress(@Var("params") PostAddressParam params)throws Exception{
		postAddress.updatePostAddress(params);
		return true;
	}
	
	@Field("remove")
	public boolean removePostAddress(@Var("params") PostAddressParam params)throws Exception{
		postAddress.removePostAddress(params);
		return true;
	}

}

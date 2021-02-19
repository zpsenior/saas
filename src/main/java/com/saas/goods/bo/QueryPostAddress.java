package com.saas.goods.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.goods.dao.DAOPostAddress;
import com.saas.goods.request.PostAddressParam;
import com.saas.goods.vo.PostAddress;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("QueryPostAddress")
public class QueryPostAddress {
	
	@Autowired
	private DAOPostAddress postAddress;

	@Field("addressList")
	public List<PostAddress> queryPostAddressList(@Var("params") PostAddressParam params)throws Exception{
		return postAddress.queryPostAddressList(params);
	}

	@Field("address")
	public PostAddress getPostAddress(@Var("tenantId") String tenantId, @Var("customerId") long customerId, @Var("addressId") long addressId)throws Exception{
		PostAddress params = new PostAddress();
		params.setTenantId(tenantId);
		params.setCustomerId(customerId);
		params.setAddressId(addressId);
		return postAddress.getPostAddress(params);
	}

}

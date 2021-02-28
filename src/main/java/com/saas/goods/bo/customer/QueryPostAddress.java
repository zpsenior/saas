package com.saas.goods.bo.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.session.CustomerSession;
import com.saas.goods.dao.DAOPostAddress;
import com.saas.goods.request.PostAddressParam;
import com.saas.goods.vo.PostAddress;
import com.saas.pub.BOBase;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("Customer.QueryPostAddress")
public class QueryPostAddress extends BOBase {
	
	@Autowired
	private DAOPostAddress postAddress;

	@Field("addressList")
	public List<PostAddress> queryPostAddressList()throws Exception{
		PostAddressParam params = new PostAddressParam();
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setCustomerId(session.getCustomerId());
		return postAddress.queryPostAddressList(params);
	}

	@Field("address")
	public PostAddress getPostAddress(@Var("addressId") long addressId)throws Exception{
		PostAddress params = new PostAddress();
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setCustomerId(session.getCustomerId());
		params.setAddressId(addressId);
		return postAddress.getPostAddress(params);
	}

}

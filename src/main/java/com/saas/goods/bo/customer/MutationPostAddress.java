package com.saas.goods.bo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.session.CustomerSession;
import com.saas.goods.dao.DAOPostAddress;
import com.saas.goods.request.PostAddressParam;
import com.saas.pub.BOBase;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("Customer.MutationPostAddress")
public class MutationPostAddress extends BOBase {
	
	@Autowired
	private DAOPostAddress postAddress;
	
	@Field("addMyAddress")
	public boolean addMyPostAddress(@Var("params") PostAddressParam params)throws Exception{
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setCustomerId(session.getCustomerId());
		postAddress.addPostAddress(params);
		return true;
	}
	
	
	@Field("updateMyAddress")
	public boolean updateMyPostAddress(@Var("params") PostAddressParam params)throws Exception{
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setCustomerId(session.getCustomerId());
		postAddress.updatePostAddress(params);
		return true;
	}
	
	@Field("removeMyAddress")
	public boolean removeMyPostAddress(@Var("addressId") long addressId)throws Exception{
		PostAddressParam params = new PostAddressParam();
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setCustomerId(session.getCustomerId());
		postAddress.removePostAddress(params);
		return true;
	}

}

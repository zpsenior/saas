package com.saas.goods.bo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.session.CustomerSession;
import com.saas.goods.dao.DAOService;
import com.saas.goods.request.ServiceParam;
import com.saas.pub.BOBase;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("Customer.MutationGoodsService")
public class MutationGoodsService extends BOBase {
	
	@Autowired
	private DAOService service;

	@Field("create")
	public boolean createService(@Var("params") ServiceParam params)throws Exception{
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setCustomerId(session.getCustomerId());
		service.addService(params);
		return true;
	}
	
	@Field("appointment")
	public boolean appointment(@Var("params") ServiceParam params) throws Exception{
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setCustomerId(session.getCustomerId());
		service.updateService(params);
		return true;
	}

}

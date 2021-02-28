package com.saas.goods.bo.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.session.TenantStaffSession;
import com.saas.goods.dao.DAOService;
import com.saas.goods.request.ServiceParam;
import com.saas.pub.BOBase;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("Staff.MutationGoodsService")
public class MutationGoodsService extends BOBase {
	
	@Autowired
	private DAOService service;

	@Field("create")
	public boolean createService(@Var("params") ServiceParam params)throws Exception{
		TenantStaffSession session = getStaffSession();
		params.setTenantId(session.getTenantId());
		service.addService(params);
		return true;
	}
	
	@Field("appointment")
	public boolean appointment(@Var("params") ServiceParam params) throws Exception{
		TenantStaffSession session = getStaffSession();
		params.setTenantId(session.getTenantId());
		service.updateService(params);
		return true;
	}

}

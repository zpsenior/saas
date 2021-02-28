package com.saas.auth.bo.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.dao.DAOTenant;
import com.saas.auth.session.AdminSession;
import com.saas.auth.vo.Tenant;
import com.saas.pub.BOBase;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("Admin.MutationTenant")
public class MutationTenant extends BOBase {
	

	@Autowired
	private DAOTenant tenant;
	
	@Field("add")
	public boolean addTenant(@Var("params") Tenant params)throws Exception{
		AdminSession session = getAdminSession();
		params.setOperator(session.getAdminId());
		tenant.addTenant(params);
		return true;
	}
	
	@Field("update")
	public boolean updateTenantInfo(@Var("params") Tenant params)throws Exception{
		tenant.updateTenant(params);
		return true;
	}

}

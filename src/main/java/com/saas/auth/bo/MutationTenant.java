package com.saas.auth.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.dao.DAOTenant;
import com.saas.auth.vo.Tenant;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("MutationTenant")
public class MutationTenant {
	

	@Autowired
	private DAOTenant tenant;
	
	@Field("apply")
	public boolean applyTenant(@Var("params") Tenant params)throws Exception{
		tenant.addTenant(params);
		return true;
	}
	
	@Field("update")
	public boolean updateTenantInfo(@Var("params") Tenant params)throws Exception{
		tenant.updateTenant(params);
		return true;
	}
	
	@Field("approve")
	public boolean approveTenant(@Var("tenantId") String tenantId)throws Exception{
		Tenant params = new Tenant();
		params.setTenantId(tenantId);
		//?
		tenant.updateTenant(params);
		return true;
	}

}

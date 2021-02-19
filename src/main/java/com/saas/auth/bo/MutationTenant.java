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
	public void applyTenant(@Var("params") Tenant params)throws Exception{
		tenant.addTenant(params);
	}
	
	@Field("update")
	public void updateTenantInfo(@Var("params") Tenant params)throws Exception{
		tenant.updateTenant(params);
	}
	
	@Field("approve")
	public void approveTenant(@Var("tenantId") String tenantId)throws Exception{
		Tenant params = new Tenant();
		params.setTenantId(tenantId);
		//?
		tenant.updateTenant(params);
	}

}

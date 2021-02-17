package com.saas.auth.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.dao.DAOTenant;
import com.saas.auth.request.QueryTenantParam;
import com.saas.auth.vo.Tenant;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("QueryTenant")
public class QueryTenant {
	
	@Autowired
	private DAOTenant tenant;
	
	@Field("tenants")
	public List<Tenant> queryTenantList(@Var("params") QueryTenantParam params)throws Exception{
		return tenant.queryTenantList(params);
	}

	@Field("tenant")
	public Tenant getTenant(@Var("tenantId") String tenantId)throws Exception{
		Tenant params = new Tenant();
		params.setTenantId(tenantId);
		return tenant.getTenant(params);
	}

}

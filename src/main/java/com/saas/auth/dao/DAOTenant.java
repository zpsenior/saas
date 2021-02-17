package com.saas.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.saas.auth.request.QueryTenantParam;
import com.saas.auth.vo.Tenant;

public interface DAOTenant {

	@Select({"select * from auth_tenant"})
	public List<Tenant> queryTenantList(QueryTenantParam params)throws Exception;

	@Select({"select * from auth_tenant where tenant_id=#{tenantId}"})
	public Tenant getTenant(Tenant tenant)throws Exception;

	public void addTenant(Tenant tenant)throws Exception;
	
	public void updateTenant(Tenant tenant)throws Exception;
}

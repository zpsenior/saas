package com.saas.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.saas.auth.request.QueryTenantParam;
import com.saas.auth.vo.Tenant;

@Mapper
public interface DAOTenant {

	@Select({"select * from auth_tenant"})
	public List<Tenant> queryTenantList(QueryTenantParam params)throws Exception;

	@Select({"select * from auth_tenant where tenant_id=#{tenantId}"})
	public Tenant getTenant(Tenant tenant)throws Exception;

	@Insert({
		"insert into auth_tenant(",
		"     tenant_id,  tenant_name,   license,  legal_person,  legal_person_id,   mobileno,   address,  create_date,  approval_date",
		")values(",
		"   #{tenantId},#{tenantName},#{license},#{legalPerson},#{legalPersonId},#{mobileno},#{address},   now()   ,#{approvalDate})"
		})
	public void addTenant(Tenant tenant)throws Exception;
	
	@Update({
		"<script>",
		"update auth_tenant",
		"<trim prefix='set' suffixOverrides=','>",
		"   <if test=' tenantName != null'>tenant_name=#{tenantName}</if>",
		"   <if test=' license != null'>license=#{license}</if>",
		"   <if test=' legalPerson != null'>legal_person=#{legalPerson}</if>",
		"   <if test=' legalPersonId != null'>legal_person_id=#{legalPersonId}</if>",
		"   <if test=' mobileno != null'>mobileno=#{mobileno}</if>",
		"   <if test=' address != null'>address=#{address}</if>",
		"   <if test='true'>update_date=now()</if>",
		"   <if test=' approvalDate != null'>approval_date=#{approvalDate}</if>",
		"</trim>",
		"where tenant_id=#{tenantId}",
		"</script>"
		})
	public void updateTenant(Tenant tenant)throws Exception;
}

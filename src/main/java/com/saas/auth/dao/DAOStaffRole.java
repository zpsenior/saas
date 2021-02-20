package com.saas.auth.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import com.saas.auth.vo.TenantStaffRole;

public interface DAOStaffRole {
	
	@Insert({
		"insert into auth_tenant_staff_role(",
		"     tenant_id,   descript,   privileges,  create_date",
		")values(",
		"   #{tenantId},#{descript},#{privileges},   now()   )"
		})
	public void addStaffRole(TenantStaffRole role)throws Exception;
	
	@Update({
		"update auth_tenant_staff_role",
		"<trim prefix='set' suffixOverrides=','>",
		"   <if test=' descript != null'>descript=#{descript}</if>",
		"   <if test=' privileges != null'>privileges=#{privileges}</if>",
		"   <if test='true'>update_date=now()</if>",
		"</trim>",
		"where tenant_id=#{tenantId}",
		"  and role_id=#{roleId}"
		})
	public void updateStaffRole(TenantStaffRole role)throws Exception;

}

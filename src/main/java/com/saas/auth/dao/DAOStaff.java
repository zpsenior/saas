package com.saas.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.saas.auth.request.QueryStaffParam;
import com.saas.auth.vo.TenantStaff;

public interface DAOStaff {

	@Select({"select * from auth_staff where tenant_id=#{tenantId}"})
	public List<TenantStaff> queryStaffList(QueryStaffParam param)throws Exception;

	@Select({"select * from auth_staff where tenant_id=#{tenantId} and staff_id=#{staffId}"})
	public TenantStaff getStaff(TenantStaff staff)throws Exception;

	@Insert({
		"insert into auth_tenant_staff(",
		"     tenant_id,  user_id,   openid,  login_name,   nickname,   mobileno,   email,   status,  create_date,  staff_roles",
		")values(",
		"   #{tenantId},#{userId},#{openid},#{loginName},#{nickname},#{mobileno},#{email},#{status},   now()   ,#{staffRoles})"
		})
	public void addStaff(TenantStaff staff)throws Exception;
	
	@Update({
		"update auth_tenant_staff",
		"<trim prefix='set' suffixOverrides=','>",
		"   <if test=' userId != null'>user_id=#{userId}</if>",
		"   <if test=' openid != null'>openid=#{openid}</if>",
		"   <if test=' loginName != null'>login_name=#{loginName}</if>",
		"   <if test=' nickname != null'>nickname=#{nickname}</if>",
		"   <if test=' mobileno != null'>mobileno=#{mobileno}</if>",
		"   <if test=' email != null'>email=#{email}</if>",
		"   <if test=' status != null'>status=#{status}</if>",
		"   <if test='true'>update_date=now()</if>",
		"   <if test=' staffRoles != null'>staff_roles=#{staffRoles}</if>",
		"</trim>",
		"where tenant_id=#{tenantId}",
		"  and staff_id=#{staffId}"
		})
	public void updateStaff(TenantStaff staff)throws Exception;
}

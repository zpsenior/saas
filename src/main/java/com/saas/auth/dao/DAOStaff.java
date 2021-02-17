package com.saas.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.saas.auth.request.QueryStaffParam;
import com.saas.auth.vo.TenantStaff;

public interface DAOStaff {

	@Select({"select * from auth_staff where tenant_id=#{tenantId}"})
	public List<TenantStaff> queryStaffList(QueryStaffParam param)throws Exception;

	@Select({"select * from auth_staff where tenant_id=#{tenantId} and staff_id=#{staffId}"})
	public TenantStaff getStaff(TenantStaff staff)throws Exception;

	public void addStaff(TenantStaff staff)throws Exception;
	
	public void updateStaff(TenantStaff staff)throws Exception;
}

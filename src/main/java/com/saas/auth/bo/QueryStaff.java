package com.saas.auth.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.dao.DAOStaff;
import com.saas.auth.request.QueryStaffParam;
import com.saas.auth.vo.TenantStaff;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("QueryStaff")
public class QueryStaff {
	

	@Autowired
	private DAOStaff staff;
	
	@Field("staffs")
	public List<TenantStaff> queryStaffList(@Var("params") QueryStaffParam params)throws Exception{
		return staff.queryStaffList(params);
	}
	
	@Field("staff")
	public TenantStaff getStaff(@Var("tenantId") String tenantId, @Var("staffId") Long staffId)throws Exception{
		TenantStaff params = new TenantStaff();
		params.setTenantId(tenantId);
		params.setStaffId(staffId);
		return staff.getStaff(params);
	}
}

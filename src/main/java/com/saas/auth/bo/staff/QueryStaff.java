package com.saas.auth.bo.staff;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.dao.DAOStaff;
import com.saas.auth.dao.DAOStaffRole;
import com.saas.auth.request.QueryStaffParam;
import com.saas.auth.vo.TenantStaff;
import com.saas.auth.vo.TenantStaffRole;
import com.saas.pub.BOBase;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("Staff.QueryStaff")
public class QueryStaff extends BOBase {
	

	@Autowired
	private DAOStaff staff;

	@Autowired
	private DAOStaffRole staffRole;
	
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
	

	@Field("staffRoles")
	public List<TenantStaffRole> queryStaffRoleList(@Var("params") String[] params)throws Exception{
		StringBuffer sb = new StringBuffer();
		for(String role : params) {
			if(sb.length() > 0) {
				sb.append(",");
			}
			sb.append("'").append(role).append("'");
		}
		return staffRole.queryStaffRoleList(sb.toString());
	}
}

package com.saas.auth.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.dao.DAOAdmin;
import com.saas.auth.dao.DAOAdminRole;
import com.saas.auth.request.QueryAdminParam;
import com.saas.auth.vo.Admin;
import com.saas.auth.vo.AdminRole;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("QueryAdmin")
public class QueryAdmin {
	

	@Autowired
	private DAOAdmin admin;

	@Autowired
	private DAOAdminRole adminRole;

	@Field("admins")
	public List<Admin> queryAdminList(@Var("params") QueryAdminParam params)throws Exception{
		return admin.queryAdminList(params);
	}

	@Field("admin")
	public Admin getAdmin(@Var("adminId") long adminId)throws Exception{
		return admin.getAdmin(adminId);
	}


	@Field("adminRoles")
	public List<AdminRole> queryAdminRoleList(@Var("params") String[] params)throws Exception{
		StringBuffer sb = new StringBuffer();
		for(String role : params) {
			if(sb.length() > 0) {
				sb.append(",");
			}
			sb.append("'").append(role).append("'");
		}
		return adminRole.queryAdminRoleList(sb.toString());
	}
}

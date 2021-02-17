package com.saas.auth.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.dao.DAOAdmin;
import com.saas.auth.request.QueryAdminParam;
import com.saas.auth.vo.Admin;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("QueryAdmin")
public class QueryAdmin {
	

	@Autowired
	private DAOAdmin admin;

	@Field("admins")
	public List<Admin> queryAdminList(@Var("params") QueryAdminParam params)throws Exception{
		return admin.queryAdminList(params);
	}

	@Field("admin")
	public Admin getAdmin(@Var("adminId") long adminId)throws Exception{
		return admin.getAdmin(adminId);
	}

}

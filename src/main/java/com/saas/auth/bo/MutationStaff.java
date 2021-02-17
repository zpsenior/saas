package com.saas.auth.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.dao.DAOStaff;
import com.saas.auth.request.LoginParam;
import com.saas.auth.vo.TenantStaff;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("MutationStaff")
public class MutationStaff {
	

	@Autowired
	private DAOStaff staff;
	
	@Field
	public boolean login(@Var("login") LoginParam login)throws Exception{
		return false;
	}

	@Field
	public boolean changePassword(@Var("login") LoginParam login)throws Exception{
		return false;
	}

	public boolean retrievePassword(@Var("login") LoginParam login)throws Exception{
		return false;
	}

	public boolean sendVerifyCode(@Var("login") LoginParam login)throws Exception{
		return false;
	}
	
	@Field("update")
	public void updateStaff(@Var("params") TenantStaff params)throws Exception{
		staff.updateStaff(params);
	}

	@Field("add")
	public void addStaff(@Var("params") TenantStaff params)throws Exception{
		staff.addStaff(params);
	}
}

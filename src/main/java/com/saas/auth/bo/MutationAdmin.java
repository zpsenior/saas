package com.saas.auth.bo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.dao.DAOAdmin;
import com.saas.auth.request.AdminParam;
import com.saas.auth.request.LoginParam;
import com.saas.pub.VerifyCode;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("MutationAdmin")
public class MutationAdmin {
	

	@Autowired
	private DAOAdmin admin;
	
	@Autowired
	private VerifyCode verifyCode;

	public boolean login(@Var("login") LoginParam login)throws Exception{
		return true;
	}

	public boolean changePassword(@Var("login") LoginParam login)throws Exception{
		return true;
	}

	public boolean retrievePassword(@Var("login") LoginParam login)throws Exception{
		return true;
	}

	public boolean sendVerifyCode(@Var("mobileno") String mobileno)throws Exception{
		String code = verifyCode.createCode(mobileno);
		return true;
	}

	public void updateAdmin(@Var("params") AdminParam params)throws Exception{
		admin.updateAdmin(params);
	}

}

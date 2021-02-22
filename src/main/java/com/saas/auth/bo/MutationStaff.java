package com.saas.auth.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.saas.auth.dao.DAOStaff;
import com.saas.auth.request.LoginParam;
import com.saas.auth.vo.TenantStaff;
import com.saas.pub.service.VerificationCodeService;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("MutationStaff")
public class MutationStaff {
	

	@Value("${saas.register.templateCode}")
	private String registerTemplateCode;
	
	@Autowired
	private VerificationCodeService verificationCode;
	

	@Autowired
	private DAOStaff staff;
	
	@Field
	public boolean login(@Var("params") LoginParam params)throws Exception{
		return false;
	}

	@Field
	public boolean changePassword(@Var("params") LoginParam params)throws Exception{
		return false;
	}

	public boolean retrievePassword(@Var("params") LoginParam params)throws Exception{
		return false;
	}

	public boolean sendVerifyCode(@Var("mobileno") String mobileno)throws Exception{
		verificationCode.sendCheckCode(mobileno, registerTemplateCode, 60);
		return true;
	}
	
	@Field("update")
	public boolean updateStaff(@Var("params") TenantStaff params)throws Exception{
		staff.updateStaff(params);
		return true;
	}

	@Field("add")
	public boolean addStaff(@Var("params") TenantStaff params)throws Exception{
		staff.addStaff(params);
		return true;
	}
}

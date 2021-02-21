package com.saas.auth.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.dao.DAOCustomer;
import com.saas.auth.dao.DAOUser;
import com.saas.auth.request.LoginParam;
import com.saas.auth.request.UserInfoParam;
import com.saas.auth.vo.Customer;
import com.saas.auth.vo.UserInfo;
import com.saas.pub.MD5;
import com.saas.pub.VerificationCodeService;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("MutationCustomer")
public class MutationCustomer {

	@Autowired
	private DAOCustomer customer;

	@Autowired
	private DAOUser user;
	
	private VerificationCodeService verificationCodeService;
	
	public boolean register(@Var("openid") String openid, @Var("mobileno") String mobileno, @Var("verifyCode") String verifyCode)throws Exception{
		Customer params = new Customer();
		params.setLoginName(mobileno);
		params.setMobileno(mobileno);
		params.setOpenid(openid);
		customer.addCustomer(params);
		return true;
	}
	
	public boolean login(@Var("params") LoginParam params)throws Exception{
		return false;
	}
	
	public boolean changePassword(@Var("tenantId") String tenantId, @Var("customerId") long customerId, 
			@Var("password") String password, @Var("confirm") String confirm)throws Exception{
		if(!password.equals(confirm)) {
			
		}
		Customer params = new Customer();
		params.setTenantId(tenantId);
		params.setCustomerId(customerId);
		params = customer.getCustomer(params);
		if(params == null) {
			
		}
		UserInfo userInfo = new UserInfo();
		userInfo.setTenantId(tenantId);
		userInfo.setUserId(params.getUserId());
		userInfo.setPassword(MD5.encode(password + tenantId + params.getUserId()));
		user.updateUserInfo(userInfo);
		return true;
	}

	public boolean resetPassword(@Var("params") LoginParam params)throws Exception{
		return false;
	}

	public boolean sendVerificationCode(@Var("params") LoginParam params)throws Exception{
		///verificationCodeService.sendCheckCode(mobileno, templateId, expireTime);
		return true;
	}

	public boolean updateCustomer(@Var("params") UserInfoParam params)throws Exception{
		return false;
	}

}

package com.saas.auth.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.dao.DAOCustomer;
import com.saas.auth.request.LoginParam;
import com.saas.auth.request.UserInfoParam;
import com.saas.auth.vo.Customer;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("MutationCustomer")
public class MutationCustomer {

	@Autowired
	private DAOCustomer customer;
	
	public boolean register(@Var("openid") String openid, @Var("mobileno") String mobileno, @Var("verifyCode") String verifyCode)throws Exception{
		Customer params = new Customer();
		params.setLoginName(mobileno);
		params.setMobileno(mobileno);
		params.setOpenid(openid);
		customer.addCustomer(params);
		return false;
	}
	
	public boolean login(@Var("params") LoginParam params)throws Exception{
		return false;
	}
	
	public boolean changePassword(@Var("params") LoginParam params)throws Exception{
		return false;
	}

	public boolean retrievePassword(@Var("params") LoginParam params)throws Exception{
		return false;
	}

	public boolean sendVerifyCode(@Var("params") LoginParam params)throws Exception{
		return false;
	}

	public boolean updateCustomer(@Var("params") UserInfoParam params)throws Exception{
		return false;
	}

}

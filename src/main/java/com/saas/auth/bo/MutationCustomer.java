package com.saas.auth.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.saas.auth.dao.DAOCustomer;
import com.saas.auth.dao.DAOUser;
import com.saas.auth.session.CustomerSession;
import com.saas.auth.vo.Customer;
import com.saas.auth.vo.UserInfo;
import com.saas.auth.wechat.WechatAuth;
import com.saas.auth.wechat.WechatAuth.AuthToken;
import com.saas.pub.MD5;
import com.saas.pub.service.CacheService;
import com.saas.pub.service.VerificationCodeService;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("MutationCustomer")
public class MutationCustomer {
	
	@Value("${saas.register.templateCode}")
	private String registerTemplateCode;

	@Autowired
	private DAOCustomer customer;

	@Autowired
	private DAOUser user;
	
	@Autowired
	private WechatAuth wechatAuth;
	
	@Autowired
	private CacheService cacheService;
	
	@Autowired
	private VerificationCodeService verificationCodeService;
	
	@Field
	public boolean bind(@Var("tenantId") String tenantId, @Var("nickname") String nickname
			, @Var("mobileno") String mobileno, @Var("password") String password)throws Exception{
		CustomerSession session = CustomerSession.getInstance(cacheService, new CustomerSession());
		String openid = session.getOpenid();
		String unionid = session.getUnionid();
		UserInfo userInfo = new UserInfo();
		userInfo.setTenantId(tenantId);
		userInfo.setMobileno(mobileno);
		userInfo.setUnionid(unionid);
		if(password != null && !"".equals(password)) {
			userInfo.setPassword(MD5.encode(password + openid));
		}
		user.addUserInfo(userInfo);
		Customer params = new Customer();
		params.setUserId(userInfo.getUserId());
		params.setTenantId(tenantId);
		params.setLoginName(mobileno);
		params.setNickname(nickname);
		params.setMobileno(mobileno);
		params.setOpenid(openid);
		customer.addCustomer(params);
		return true;
	}
	
	@Field
	public CustomerSession login(@Var("wxcode") String wxcode)throws Exception{
		CustomerSession session = new CustomerSession();
		AuthToken token = wechatAuth.code2Session(wxcode);
		session.setOpenid(token.getOpenid());
		session.setUnionid(token.getUnionid());
		session.setSessionKey(token.getSessionKey());
		token = wechatAuth.getAccessToken(wxcode);
		session.setAccessToken(token.getAccessToken());
		session.setRefreshToken(token.getRefreshToken());
		session.setExpiresIn(token.getExpiresIn());
		Customer params = new Customer();
		params.setOpenid(session.getOpenid());
		params = customer.getCustomerByOpenid(params);
		if(params != null) {
			session.setTenantId(params.getTenantId());
			session.setCustomerId(params.getCustomerId());
			session.setUserId(params.getUserId());
			session.save(cacheService);
		}
		return session;
	}
	
	@Field
	public boolean changePassword(@Var("password") String password, @Var("confirm") String confirm)throws Exception{
		CustomerSession session = CustomerSession.getInstance(cacheService, new CustomerSession());
		String tenantId = session.getTenantId();
		long customerId = session.getCustomerId();
		if(!password.equals(confirm)) {
			throw new RuntimeException("confirm password is different!");
		}
		Customer params = new Customer();
		params.setTenantId(tenantId);
		params.setCustomerId(customerId);
		params = customer.getCustomer(params);
		if(params == null) {
			throw new RuntimeException("can not find customer :" + customerId + " in tenant:" + tenantId);
		}
		UserInfo userInfo = new UserInfo();
		userInfo.setTenantId(tenantId);
		userInfo.setUserId(params.getUserId());
		userInfo.setPassword(MD5.encode(password + tenantId + params.getUserId()));
		user.updateUserInfo(userInfo);
		return true;
	}

	@Field
	public boolean sendVerificationCode(@Var("mobileno") String mobileno)throws Exception{
		verificationCodeService.sendCheckCode(mobileno, registerTemplateCode, 60);
		return true;
	}

}

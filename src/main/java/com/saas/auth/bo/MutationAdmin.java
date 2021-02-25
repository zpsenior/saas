package com.saas.auth.bo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.saas.auth.dao.DAOAdmin;
import com.saas.auth.request.AdminParam;
import com.saas.auth.session.AdminSession;
import com.saas.auth.vo.Admin;
import com.saas.auth.wechat.WechatAuth;
import com.saas.auth.wechat.WechatAuth.AuthToken;
import com.saas.pub.SignUtils;
import com.saas.pub.service.CacheService;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("MutationAdmin")
public class MutationAdmin {
	

	@Value("${saas.register.templateCode}")
	private String registerTemplateCode;
	
	@Value("${saas.admin.bind.scanQRcodeCallback}")
	private String bindRedirectUrl;
	
	@Value("${saas.admin.login.scanQRcodeCallback}")
	private String loginRedirectUrl;
	

	@Autowired
	private DAOAdmin admin;
	
	@Autowired
	private WechatAuth wechatAuth;
	
	@Autowired
	private CacheService cacheService;
	
	private final static String ADD_ADMIN_PREFIX = "add.admin.";
	

	@Field
	public String buildAddAdminStr(@Var("mobileno") String mobileno, @Var("nickname") String nickname)throws Exception {
		String state = SignUtils.randomString(32);
		Admin params = new Admin();
		params.setMobileno(mobileno);
		params.setNickname(nickname);
		cacheService.setProperty(ADD_ADMIN_PREFIX + state, params, 6000);
		return wechatAuth.buildQrCodeRequest(bindRedirectUrl, state);
	}
	
	@Field
	public boolean bindAdmin(@Var("code") String wxcode, @Var("state") String state)throws Exception{
		Admin ad = (Admin)cacheService.getProperty(ADD_ADMIN_PREFIX + state);
		String nickname = ad.getNickname();
		String mobileno = ad.getMobileno();
		if(mobileno == null || nickname == null) {
			throw new RuntimeException("can not any staff info");
		}
		AuthToken token = wechatAuth.code2Session(wxcode);
		Admin params = new Admin();
		params.setLoginName(mobileno);
		params.setNickname(nickname);
		params.setMobileno(mobileno);
		params.setOpenid(token.getOpenid());
		admin.addAdmin(params);
		return true;
	}
	
	@Field
	public String buildLoginAdminStr()throws Exception {
		String state = SignUtils.randomString(32);
		return wechatAuth.buildQrCodeRequest(loginRedirectUrl, state);
	}

	@Field
	public AdminSession login(@Var("code") String wxcode)throws Exception{
		AdminSession session = new AdminSession();
		AuthToken token = wechatAuth.code2Session(wxcode);
		session.setOpenid(token.getOpenid());
		session.setUnionid(token.getUnionid());
		session.setSessionKey(token.getSessionKey());
		token = wechatAuth.getAccessToken(wxcode);
		session.setAccessToken(token.getAccessToken());
		session.setRefreshToken(token.getRefreshToken());
		session.setExpiresIn(token.getExpiresIn());
		Admin params = new Admin();
		params.setOpenid(session.getOpenid());
		params = admin.getStaffByOpenid(params);
		if(params != null) {
			session.setAdminId(params.getAdminId());
			session.save(cacheService);
			return session;
		}
		throw new RuntimeException("can not find that staff");
	}

	public boolean updateAdmin(@Var("params") AdminParam params)throws Exception{
		admin.updateAdmin(params);
		return true;
	}

}

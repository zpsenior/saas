package com.saas.auth.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.saas.auth.dao.DAOStaff;
import com.saas.auth.dao.DAOUser;
import com.saas.auth.session.TenantStaffSession;
import com.saas.auth.vo.TenantStaff;
import com.saas.auth.vo.UserInfo;
import com.saas.auth.wechat.WechatAuth;
import com.saas.auth.wechat.WechatAuth.AuthToken;
import com.saas.pub.SignUtils;
import com.saas.pub.service.CacheService;
import com.saas.pub.service.VerificationCodeService;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("MutationStaff")
public class MutationStaff {
	

	@Value("${saas.register.templateCode}")
	private String registerTemplateCode;
	
	@Value("${saas.staff.bind.scanQRcodeCallback}")
	private String bindRedirectUrl;
	
	@Value("${saas.staff.login.scanQRcodeCallback}")
	private String loginRedirectUrl;
	
	@Autowired
	private VerificationCodeService verificationCode;

	@Autowired
	private DAOUser user;

	@Autowired
	private DAOStaff staff;
	
	@Autowired
	private WechatAuth wechatAuth;
	
	@Autowired
	private CacheService cacheService;
	
	private final static String ADD_STAFF_PREFIX = "add.staff.";
	
	
	public String buildAddStaffStr(@Var("mobileno") String mobileno, @Var("nickname") String nickname)throws Exception {
		TenantStaffSession session = TenantStaffSession.getInstance(cacheService, new TenantStaffSession());
		String state = SignUtils.randomString(32);
		TenantStaff params = new TenantStaff();
		params.setMobileno(mobileno);
		params.setNickname(nickname);
		params.setTenantId(session.getTenantId());
		cacheService.setProperty(ADD_STAFF_PREFIX + state, params, 6000);
		return wechatAuth.buildQrCodeRequest(bindRedirectUrl, state);
	}

	
	@Field
	public boolean bindStaff(@Var("code") String wxcode, @Var("state") String state)throws Exception{
		
		TenantStaff ts = (TenantStaff)cacheService.getProperty(ADD_STAFF_PREFIX + state);
		String tenantId = ts.getTenantId();
		String nickname = ts.getNickname();
		String mobileno = ts.getMobileno();
		if(tenantId == null || mobileno == null || nickname == null) {
			throw new RuntimeException("can not any staff info");
		}
		AuthToken token = wechatAuth.code2Session(wxcode);
		UserInfo userInfo = new UserInfo();
		userInfo.setTenantId(tenantId);
		userInfo.setMobileno(mobileno);
		userInfo.setUnionid(token.getUnionid());
		user.addUserInfo(userInfo);
		TenantStaff params = new TenantStaff();
		params.setUserId(userInfo.getUserId());
		params.setTenantId(tenantId);
		params.setLoginName(mobileno);
		params.setNickname(nickname);
		params.setMobileno(mobileno);
		params.setOpenid(token.getOpenid());
		staff.addStaff(params);
		return true;
	}
	
	@Field
	public String buildLoginStaffStr()throws Exception {
		String state = SignUtils.randomString(32);
		return wechatAuth.buildQrCodeRequest(loginRedirectUrl, state);
	}
	
	@Field
	public TenantStaffSession login(@Var("code") String wxcode, @Var("state") String state)throws Exception{
		TenantStaffSession session = new TenantStaffSession();
		AuthToken token = wechatAuth.code2Session(wxcode);
		session.setOpenid(token.getOpenid());
		session.setUnionid(token.getUnionid());
		session.setSessionKey(token.getSessionKey());
		token = wechatAuth.getAccessToken(wxcode);
		session.setAccessToken(token.getAccessToken());
		session.setRefreshToken(token.getRefreshToken());
		session.setExpiresIn(token.getExpiresIn());
		TenantStaff params = new TenantStaff();
		params.setOpenid(session.getOpenid());
		params = staff.getStaffByOpenid(params);
		if(params != null) {
			session.setTenantId(params.getTenantId());
			session.setStaffId(params.getStaffId());
			session.setUserId(params.getUserId());
			session.save(cacheService);
			return session;
		}
		throw new RuntimeException("can not find that staff");
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

package com.saas.pub;

import org.springframework.beans.factory.annotation.Autowired;

import com.saas.auth.session.AdminSession;
import com.saas.auth.session.CustomerSession;
import com.saas.auth.session.TenantStaffSession;
import com.saas.pub.service.CacheService;

public class BOBase {
	
	@Autowired
	protected CacheService cacheService;

	public CustomerSession getCustomerSession() {
		return CustomerSession.getInstance(cacheService, new CustomerSession());
	}

	public TenantStaffSession getStaffSession() {
		return TenantStaffSession.getInstance(cacheService, new TenantStaffSession());
	}

	public AdminSession getAdminSession() {
		return AdminSession.getInstance(cacheService, new AdminSession());
	}
}

package com.saas.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.saas.auth.session.AdminSession;
import com.saas.auth.session.CustomerSession;
import com.saas.auth.session.TenantStaffSession;
import com.saas.auth.wechat.WechatAuth;
import com.saas.pub.service.CacheService;
import com.saas.wpay.WPayNotify;

@Configuration
public class AppConfig implements WebMvcConfigurer{
	
	@Value("${saas.wechat.appId}")
	private String appId;
	
	@Value("${saas.wechat.secret}")
	private String secret;
	
	@Value("${graphql.inputClassPackages}")
	private String inputClassPackages;
	
	@Value("${wpay.notify.urlPattern}")
	private String wpayNotifyPattern;
	
	
	
	@Value("${graphql.customer.queryClass}")
	private String customerQueryClass;
	
	@Value("${graphql.customer.mutationClass}")
	private String customerMutationClass;
	
	@Value("${graphql.customer.qlFile}")
	private String customerQLFile;
	
	@Value("${graphql.customer.urlPattern}")
	private String customerUrlPattern;
	

	@Value("${graphql.staff.queryClass}")
	private String staffQueryClass;
	
	@Value("${graphql.staff.mutationClass}")
	private String staffMutationClass;
	
	@Value("${graphql.staff.qlFile}")
	private String staffQLFile;
	
	@Value("${graphql.staff.urlPattern}")
	private String staffUrlPattern;
	

	@Value("${graphql.admin.queryClass}")
	private String adminQueryClass;
	
	@Value("${graphql.admin.mutationClass}")
	private String adminMutationClass;
	
	@Value("${graphql.admin.qlFile}")
	private String adminQLFile;
	
	@Value("${graphql.admin.urlPattern}")
	private String adminUrlPattern;
	
	@Autowired
	private WPayNotify notify;
	
	@Autowired
	private CacheService cacheService;
	
	public void addInterceptors(InterceptorRegistry registry) {
		SecurityInterceptor interceptor = new SecurityInterceptor(cacheService, new CustomerSession());
		try{
			interceptor.init(customerQueryClass, customerMutationClass, inputClassPackages, customerQLFile);
		}catch(Exception e) {
			e.printStackTrace();
			return;
		}
		registry.addInterceptor(interceptor).addPathPatterns(customerUrlPattern);
		interceptor = new SecurityInterceptor(cacheService, new TenantStaffSession());
		try{
			interceptor.init(staffQueryClass, staffMutationClass, inputClassPackages, staffQLFile);
		}catch(Exception e) {
			e.printStackTrace();
			return;
		}
		registry.addInterceptor(interceptor).addPathPatterns(staffUrlPattern);
		interceptor = new SecurityInterceptor(cacheService, new AdminSession());
		try{
			interceptor.init(adminQueryClass, adminMutationClass, inputClassPackages, adminQLFile);
		}catch(Exception e) {
			e.printStackTrace();
			return;
		}
		registry.addInterceptor(interceptor).addPathPatterns(adminUrlPattern);
		if(wpayNotifyPattern != null) {
			registry.addInterceptor(notify).addPathPatterns(wpayNotifyPattern);
		}
	}
	
	@Bean
	public WechatAuth getWechatAuth() {
		return new WechatAuth(appId, secret);
	}
}

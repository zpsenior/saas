package com.saas.training;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer{
	
	@Value("${graphql.queryClass}")
	private String queryClass;
	
	@Value("${graphql.mutationClass}")
	private String mutationClass;
	
	@Value("${graphql.inputClassPackage}")
	private String inputClassPackage;
	
	@Value("${graphql.qlFile}")
	private String qlFile;
	
	@Value("${graphql.urlPattern}")
	private String urlPattern;
	
	public void addInterceptors(InterceptorRegistry registry) {
		if(queryClass != null && mutationClass != null && qlFile != null) {
			SecurityInterceptor interceptor = new SecurityInterceptor();
			try{
				interceptor.init(queryClass, mutationClass, inputClassPackage, qlFile);
			}catch(Exception e) {
				e.printStackTrace();
				return;
			}
			registry.addInterceptor(interceptor).addPathPatterns(urlPattern);
		}
	}
}

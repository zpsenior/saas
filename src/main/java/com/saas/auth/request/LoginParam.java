package com.saas.auth.request;

import com.zpsenior.graphql4j.annotation.Input;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Input("LoginParam")
public class LoginParam {
	
	private String loginName;
	
	private String password;
	
	private String newPassword;
	
	private String confirm;
	
	private String verifyCode;
}

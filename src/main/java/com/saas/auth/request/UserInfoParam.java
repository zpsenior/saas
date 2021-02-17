package com.saas.auth.request;

import com.zpsenior.graphql4j.annotation.Input;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Input("UserInfoParam")
public class UserInfoParam {

	private String tenantId;
	
	private long userId;

	private String nickname;

	private String mobile;

	private String email;

}

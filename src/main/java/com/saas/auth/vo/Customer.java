package com.saas.auth.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="客户表", incr="customerId")
public class Customer {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="客户ID")
	private long customerId;

	@Field(desc="用户ID")
	private long userId;

	@Field(desc="微信openid", len=64)
	private String openid;

	@Field(desc="登录名")
	private String loginName;

	@Field(desc="昵称")
	private String nickname;

	@Field(desc="联系电话")
	private String mobileno;

	@Field(desc="电子油箱")
	private String email;

	@Field(desc="创建日期")
	private Date createDate;

	@Field(desc="人员状态")
	private UserStatus status;
}

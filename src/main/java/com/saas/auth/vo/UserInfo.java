package com.saas.auth.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="用户信息表", incr="userId")
public class UserInfo {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(desc="用户ID")
	private long userId;
	
	@Field(desc="性别")
	private Gender gender;
	
	@Field(desc="联系电话")
	private String mobileno;

	@Field(desc="电子邮箱")
	private String email;

	@Field(desc="证件类型", len=2)
	private String idType;

	@Field(desc="证件号码")
	private String idNo;

	@Field(desc="真实姓名")
	private String trueName;

	@Field(desc="修改日期")
	private Date updateDate;

	@Field(desc="创建日期")
	private Date createDate;

	@Field(desc="密码")
	private String password;

}

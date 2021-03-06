package com.saas.auth.vo;

import java.util.Date;
import java.util.List;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Join;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="管理员表", incr="adminId")
public class Admin {


	@Field(isKey=true, desc="租户ID")
	private long adminId;

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

	@Field(desc="人员状态")
	private UserStatus status;
	
	@Field(desc="用户角色ID", len=100)
	private String[] adminRoles;

	@Field(desc="修改日期")
	private Date updateDate;

	@Field(desc="创建日期")
	private Date createDate;
	
	@Join(request = "QueryAdmin.queryAdminRoleList", params = { "adminRoles" })
	private List<AdminRole> roles;
}

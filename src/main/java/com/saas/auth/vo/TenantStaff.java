package com.saas.auth.vo;

import java.util.Date;
import java.util.List;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Join;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="租户人员表", incr="staffId")
public class TenantStaff {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="员工ID")
	private long staffId;
	
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

	@Field(desc="状态")
	private UserStatus status;

	@Field(desc="修改日期")
	private Date updateDate;

	@Field(desc="创建日期")
	private Date createDate;
	
	@Field(desc="用户角色ID", len=1000)
	private String[] staffRoles;
	
	@Join(request = "queryStaffRoleList", params = { "tenantId", "staffRoles" })
	private List<TenantStaffRole> roles;
}

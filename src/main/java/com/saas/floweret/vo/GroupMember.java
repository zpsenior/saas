package com.saas.floweret.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="群员表")
public class GroupMember {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="群ID")
	private long groupId;

	@Field(isKey=true, desc="用户ID")
	private long customerId;

	@Field(desc="成员ID")
	private long memberNo;
	
	@Field(desc="成员角色")
	private MemberPrivilege privilege;
	
	@Field(desc="支付金额")
	private long pay;

	@Field(desc="创建日期")
	private Date createDate;
}

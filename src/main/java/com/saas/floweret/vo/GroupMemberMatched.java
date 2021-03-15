package com.saas.floweret.vo;


import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="群组成员匹配表")
public class GroupMemberMatched {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="群ID")
	private long groupId;

	@Field(isKey=true, desc="用户ID")
	private long customerId;

	@Field(isKey=true, desc="关注用户ID")
	private long attentionCustomerId;

	@Field(desc="创建日期")
	private Date createDate;

}

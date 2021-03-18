package com.saas.floweret.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="用户投票表")
public class MemberVoting {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="用户ID")
	private long customerId;

	@Field(isKey=true, desc="投票ID")
	private long votingId;

	@Field(isKey=true, desc="投票选择ID")
	private long choiceId;

	@Field(desc="创建日期")
	private Date createDate;

}

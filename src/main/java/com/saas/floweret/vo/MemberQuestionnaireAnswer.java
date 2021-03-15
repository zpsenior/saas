package com.saas.floweret.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="用户调查答案表")
public class MemberQuestionnaireAnswer {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="用户ID")
	private long customerId;

	@Field(isKey=true, desc="问题ID")
	private long questionId;

	@Field(isKey=true, desc="问题答案ID")
	private long answerId;

	@Field(desc="创建日期")
	private Date createDate;

}

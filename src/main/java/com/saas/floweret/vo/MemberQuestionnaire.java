package com.saas.floweret.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="用户调查表")
public class MemberQuestionnaire {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="用户ID")
	private long customerId;

	@Field(isKey=true, desc="问题ID")
	private long questionId;

	@Field(desc="开始时间")
	private Date beginTime;

	@Field(desc="结束时间")
	private Date endTime;
	
	@Field(desc="总得分")
	private int[] totalScore;

	@Field(desc="创建日期")
	private Date createDate;

}

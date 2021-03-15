package com.saas.floweret.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="点赞表", incr="praiseId")
public class MemberCirclePraise {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="用户ID")
	private long customerId;

	@Field(isKey=true, desc="点赞ID")
	private long praiseId;

	@Field(desc="circleID")
	private long circleId;

	@Field(desc="创建日期")
	private Date createDate;

}

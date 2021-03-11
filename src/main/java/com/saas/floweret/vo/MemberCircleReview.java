package com.saas.floweret.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="个人展示表", incr="reviewId")
public class MemberCircleReview {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="用户ID")
	private long customerId;

	@Field(isKey=true, desc="评论ID")
	private long reviewId;

	@Field(desc="circleID")
	private long circleId;

	@Field(desc="引用评论ID")
	private long parentId;
	
	@Field(desc="评论内容")
	private String content;
	
	@Field(desc="是否展示")
	private boolean showing;

	@Field(desc="创建日期")
	private Date createDate;

}

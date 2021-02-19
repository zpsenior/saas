package com.saas.training.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="教师评价表", incr="reviewId")
public class TeacherReview {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="教师")
	private long staffId;

	@Field(isKey=true, desc="评价ID")
	private long reviewId;

	@Field(desc="评价用户")
	private long customerId;

	@Field(desc="评价内容", len=300)
	private String content;

	@Field(desc="创建日期")
	private Date createDate;

}

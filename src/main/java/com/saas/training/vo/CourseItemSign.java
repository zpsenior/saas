package com.saas.training.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="课程签到表")
public class CourseItemSign {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="课程ID")
	private long courseId;

	@Field(isKey=true, desc="服务明细ID")
	private long courseItemId;

	@Field(isKey=true, desc="用户ID")
	private long customerId;

	@Field(desc="签到时间")
	private Date signIn;

	@Field(desc="创建日期")
	private Date createDate;

}

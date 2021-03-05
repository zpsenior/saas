package com.saas.training.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="课程作业表", incr="workId")
public class Homework {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="课程ID")
	private long courseId;

	@Field(isKey=true, desc="课程明细ID")
	private long courseItemId;

	@Field(isKey=true, desc="作业ID")
	private long workId;

	@Field(desc="布置老师ID")
	private long staffId;

	@Field(desc="作业描述")
	private String content;

	@Field(desc="修改日期")
	private Date updateDate;

	@Field(desc="创建日期")
	private Date createDate;

}

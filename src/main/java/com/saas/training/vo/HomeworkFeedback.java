package com.saas.training.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="课程作业反馈表", incr="feedbackId")
public class HomeworkFeedback {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="作业ID")
	private long workId;

	@Field(isKey=true, desc="作业ID")
	private long feedbackId;

	@Field(desc="课程ID")
	private long courseId;

	@Field(desc="服务明细ID")
	private long courseItemId;

	@Field(desc="用户ID")
	private long customerId;

	@Field(desc="作业反馈")
	private String feedback;


	@Field(desc="创建日期")
	private Date createDate;
}

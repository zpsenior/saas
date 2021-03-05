package com.saas.training.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="课程学生表")
public class CourseStudent {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="课程ID")
	private long courseId;

	@Field(isKey=true, desc="客户ID")
	private long customerId;

	@Field(desc="订单ID")
	private long orderId;

	@Field(desc="订单明细ID")
	private long orderItemId;
	
	@Field(desc="耗费次数")
	private int spentCount;
	
	@Field(desc="总次数")
	private int totalCount;

	@Field(desc="创建日期")
	private Date createDate;

}

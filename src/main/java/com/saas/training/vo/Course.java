package com.saas.training.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="机构课程表", incr="courseId")
public class Course {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="课程ID")
	private long courseId;

	@Field(desc="客户ID")
	private long customerId;

	@Field(desc="服务员工ID")
	private long staffId;

	@Field(desc="商品ID")
	private long goodsId;

	@Field(desc="订单ID")
	private long orderId;

	@Field(desc="订单明细ID")
	private long orderItemId;
	
	@Field(desc="耗费次数")
	private int spentCount;
	
	@Field(desc="总次数")
	private int totalCount;

	@Field(desc="修改日期")
	private Date updateDate;

	@Field(desc="创建日期")
	private Date createDate;
}

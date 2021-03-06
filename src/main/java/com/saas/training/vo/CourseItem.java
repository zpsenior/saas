package com.saas.training.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="课程明细表", incr="courseItemId")
public class CourseItem {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="课程ID")
	private long courseId;

	@Field(isKey=true, desc="服务明细ID")
	private long courseItemId;

	@Field(desc="商品ID")
	private long goodsId;

	@Field(desc="服务商员工ID")
	private long staffId;

	@Field(desc="预约日期")
	private Date appointDate;

	@Field(desc="开始时间")
	private Date beginTime;

	@Field(desc="结束时间")
	private Date endTime;

	@Field(desc="图片", len=1000)
	private String[] imgs;

	@Field(desc="修改日期")
	private Date updateDate;

	@Field(desc="创建日期")
	private Date createDate;
}

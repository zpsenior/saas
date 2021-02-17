package com.saas.goods.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="商品服务明细表")
public class ServiceItem {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="服务ID")
	private long serviceId;

	@Field(isKey=true, desc="服务明细ID")
	private long itemId;

	@Field(desc="服务商员工ID")
	private String staffId;

	@Field(desc="用户ID")
	private String customerId;

	@Field(desc="开始时间")
	private Date beginTime;

	@Field(desc="结束时间")
	private Date endTime;

	@Field(desc="签到时间")
	private Date signIn;

	@Field(desc="图片")
	private String[] imgs;

	@Field(desc="创建日期")
	private Date createDate;

}

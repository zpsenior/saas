package com.saas.goods.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="商品服务明细表", incr="serviceItemId")
public class ServiceItem {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="服务ID")
	private long serviceId;

	@Field(isKey=true, desc="服务明细ID")
	private long serviceItemId;

	@Field(desc="服务商员工ID")
	private long staffId;

	@Field(desc="用户ID")
	private long customerId;

	@Field(desc="开始时间")
	private Date beginTime;

	@Field(desc="结束时间")
	private Date endTime;

	@Field(desc="签到时间")
	private Date signIn;

	@Field(desc="图片", len=1000)
	private String[] imgs;

	@Field(desc="修改日期")
	private Date updateDate;

	@Field(desc="创建日期")
	private Date createDate;

}

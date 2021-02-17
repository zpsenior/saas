package com.saas.goods.vo;


import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="商品服务表")
public class Service {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="服务ID")
	private long serviceId;

	@Field(desc="客户ID")
	private String customerId;

	@Field(desc="服务员工ID")
	private String staffId;

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

	@Field(desc="创建日期")
	private Date createDate;

}

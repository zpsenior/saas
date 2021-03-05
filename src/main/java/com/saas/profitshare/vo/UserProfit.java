package com.saas.profitshare.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="用户利润表")
public class UserProfit {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="客户ID")
	private long customerId;

	@Field(desc="总利润")
	private long totalProfit;

	@Field(desc="剩余利润")
	private long remainProfit;

	@Field(desc="修改日期")
	private Date updateDate;

	@Field(desc="创建日期")
	private Date createDate;
}

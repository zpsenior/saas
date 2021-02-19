package com.saas.payment.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="支付反馈表")
public class PaymentNotify {
	
	@Field(isKey=true, desc="租户ID")
	private String tenantId;
	
	@Field(isKey=true, desc="微信内部订单号")
	private String transactionId;
	
	@Field(isKey=true, desc="微信外部订单号")
	private String outTradeNo;

	@Field(desc="创建日期")
	private Date createDate;

}

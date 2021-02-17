package com.saas.payment.vo;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="支付反馈表")
public class PaymentCallback {
	
	@Field(isKey=true, desc="租户ID")
	private String tenantId;

}

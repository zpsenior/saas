package com.saas.floweret.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="用户信息表", incr="messageId")
public class MemberMessage {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="接收用户ID")
	private long customerId;

	@Field(isKey=true, desc="通知ID")
	private long messageId;

	@Field(desc="用户ID")
	private long senderId;

	@Field(desc="通知内容")
	private String content;

	@Field(desc="是否已读")
	private boolean read;

	@Field(desc="创建日期")
	private Date createDate;
}

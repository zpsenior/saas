package com.saas.floweret.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="通知信息表", incr="noticeId")
public class Notice {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="用户ID")
	private long customerId;

	@Field(isKey=true, desc="通知ID")
	private long noticeId;

	@Field(desc="通知内容")
	private String content;

	@Field(desc="是否已读")
	private boolean read;

	@Field(desc="创建日期")
	private Date createDate;
}

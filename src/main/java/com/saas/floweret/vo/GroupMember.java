package com.saas.floweret.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="群员表")
public class GroupMember {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="群ID")
	private long groupId;

	@Field(isKey=true, desc="用户ID")
	private long memberId;

	@Field(desc="成员ID")
	private long memberNo;

	@Field(desc="介绍人ID")
	private long introducer;

	@Field(desc="成员昵称")
	private long memberNickname;
	
	@Field(desc="状态")
	private MemberStatus status;
	
	@Field(desc="支付金额")
	private long payAmount;
	
	@Field(desc="支付订单号")
	private String outTradeNo;

	@Field(desc="创建日期")
	private Date createDate;
}

package com.saas.floweret.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="群聊表", incr="chatId")
public class GroupTopicChat {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="群话题ID")
	private long topicId;

	@Field(isKey=true, desc="聊天ID")
	private long chatId;

	@Field(desc="群ID")
	private long groupId;

	@Field(desc="用户ID")
	private long customerId;

	@Field(desc="回复的chatID")
	private long parentChatId;

	@Field(desc="内容", len=200)
	private String content;

	@Field(desc="状态")
	private boolean status;

	@Field(desc="创建日期")
	private Date createDate;

}

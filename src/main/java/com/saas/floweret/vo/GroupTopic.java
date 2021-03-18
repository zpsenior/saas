package com.saas.floweret.vo;

import java.util.Date;
import java.util.List;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Join;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="活动群话题表", incr="topicId")
public class GroupTopic {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="群ID")
	private long groupId;

	@Field(isKey=true, desc="话题ID")
	private long topicId;
	
	@Field(desc="话题名称", len=100)
	private String title;

	@Field(desc="创建日期")
	private Date createDate;

	@Join(request = "queryTopicChatList", params= {"tenantId", "topicId"})
	private List<GroupTopicChat> chats;
}

package com.saas.floweret.vo;

import java.util.Date;
import java.util.List;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Join;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="活动群表", incr="groupId")
public class Group {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="群ID")
	private long groupId;

	@Field(desc="群名称", len=100)
	private String title;
	
	@Field(desc="群介绍", len=1000)
	private String description;
	
	@Field(desc="群主")
	private long owner;
	
	@Field(desc="最大女群员数")
	private int maxFemaleCount;
	
	@Field(desc="女群员数")
	private int femaleCount;
	
	@Field(desc="最大男群员数")
	private int maxMaleCount;
	
	@Field(desc="男群员数")
	private int maleCount;

	@Field(desc="开始时间")
	private Date beginTime;

	@Field(desc="结束时间")
	private Date endTime;

	@Field(desc="是否线上活动")
	private boolean online;

	@Field(desc="群状态")
	private GroupStatus status;

	@Field(desc="修改日期")
	private Date updateDate;

	@Field(desc="创建日期")
	private Date createDate;
	
	@Join(request="queryMemberList", params= {"tenantId", "groupId"})
	private List<GroupMember> members;
}

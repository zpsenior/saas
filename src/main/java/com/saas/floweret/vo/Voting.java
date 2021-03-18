package com.saas.floweret.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="投票表", incr="votingId")
public class Voting {

	@Field(isKey=true, desc="投票ID")
	private long votingId;

	@Field(desc="投票类型")
	private int votingType;

	@Field(desc="投票描述")
	private String description;

	@Field(desc="多选")
	private boolean multiSelect;

	@Field(desc="投票人数")
	private int voteCount;

	@Field(desc="创建日期")
	private Date createDate;

}

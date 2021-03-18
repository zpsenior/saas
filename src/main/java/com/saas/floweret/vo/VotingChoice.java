package com.saas.floweret.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="投票选择表", incr="choiceId")
public class VotingChoice {

	@Field(isKey=true, desc="投票选择ID")
	private long choiceId;

	@Field(isKey=true, desc="问题ID")
	private long votingId;
	
	@Field(desc="选择内容")
	private String content;

	@Field(desc="创建日期")
	private Date createDate;

}

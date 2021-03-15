package com.saas.floweret.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="问卷调查答案表", incr="answerId")
public class QuestionnaireAnswer {

	@Field(isKey=true, desc="问题答案ID")
	private long answerId;

	@Field(isKey=true, desc="问题ID")
	private long questionId;
	
	@Field(desc="待选答案")
	private String content;
	
	@Field(desc="答案得分")
	private int[] score;

	@Field(desc="创建日期")
	private Date createDate;

}

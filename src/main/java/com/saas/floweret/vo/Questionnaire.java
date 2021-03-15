package com.saas.floweret.vo;

import java.util.Date;
import java.util.List;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Join;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="问卷调查表", incr="questionId")
public class Questionnaire {

	@Field(isKey=true, desc="问题ID")
	private long questionId;

	@Field(isKey=true, desc="问题类型")
	private int questionType;

	@Field(desc="问题描述")
	private String description;

	@Field(desc="创建日期")
	private Date createDate;
	
	@Join(request = "getAnswerList", params= {"questionId"})
	private List<QuestionnaireAnswer> answers;
}

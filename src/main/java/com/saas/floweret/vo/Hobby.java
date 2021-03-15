package com.saas.floweret.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="兴趣爱好表", incr="id")
public class Hobby {

	@Field(isKey=true, desc="兴趣id")
	private int id;

	@Field(desc="兴趣类型")
	private HobbyType hobbyType;

	@Field(desc="兴趣描述")
	private String hobby;

	@Field(desc="创建日期")
	private Date createDate;

}

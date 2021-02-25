package com.saas.auth.vo;


import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="管理员操作流水表", incr="adminLogId")
public class AdminLog {

	@Field(isKey=true, desc="流水ID")
	private long adminLogId;

	@Field(desc="操作url")
	private String url;

	@Field(desc="操作描述")
	private String description;

	@Field(desc="操作内容")
	private String content;

	@Field(desc="创建日期")
	private Date createDate;

}

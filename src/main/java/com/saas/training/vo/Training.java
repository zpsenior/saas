package com.saas.training.vo;

import java.util.Date;
import java.util.List;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Join;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;


@Data
@Type(desc="机构表")
public class Training {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(desc="机构介绍")
	private String descript;

	@Field(desc="机构地址")
	private String address;

	@Field(desc="创建日期")
	private Date createDate;
	
	@Join(bind = "queryTeacherList", params = { "tenantId" })
	private List<Teacher> teachers;

}

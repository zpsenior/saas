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

	@Field(desc="机构名称")
	private String name;

	@Field(desc="机构介绍", len=500)
	private String description;

	@Field(desc="联系电话")
	private String moblieno;

	@Field(desc="电子邮箱")
	private String email;

	@Field(desc="机构地址", len=100)
	private String address;

	@Field(desc="修改日期")
	private Date updateDate;

	@Field(desc="创建日期")
	private Date createDate;
	
	@Join(request = "queryTeacherList", params = { "tenantId" })
	private List<Teacher> teachers;

}

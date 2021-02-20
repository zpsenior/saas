package com.saas.training.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="教师展现表", incr="circleId")
public class TeacherCircle {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="发布教师")
	private long staffId;

	@Field(isKey=true, desc="发布ID")
	private long circleId;

	@Field(desc="内容", len=300)
	private String content;

	@Field(desc="图片地址", len=300)
	private String[] imgs;

	@Field(desc="状态")
	private boolean status;

	@Field(desc="修改日期")
	private Date updateDate;

	@Field(desc="创建日期")
	private Date createDate;

}

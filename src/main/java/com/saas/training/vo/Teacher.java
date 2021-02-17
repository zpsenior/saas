package com.saas.training.vo;

import java.util.Date;
import java.util.List;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Join;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="机构教师表")
public class Teacher {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="员工ID")
	private long staffId;

	@Field(desc="头衔")
	private String title;

	@Field(desc="性格特征")
	private String character;

	@Field(desc="描述介绍")
	private String descript;

	@Field(desc="头像")
	private String portrait;
	
	@Field(desc="毕业院校")
	private String graduation;

	@Field(desc="资格认证")
	private String certificate;
	
	@Field(desc="专业特长")
	private String specialty;
	
	@Field(desc="获奖情况")
	private String prize;

	@Field(desc="创建日期")
	private Date createDate;
	
	@Join(bind = "queryTeacherReviewList", params = { "tenantId", "staffId" })
	private List<TeacherReview> reviews;

	@Join(bind = "queryTeacherCircleList", params = { "tenantId", "staffId" })
	private List<TeacherCircle> circles;

}

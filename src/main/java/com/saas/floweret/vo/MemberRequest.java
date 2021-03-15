package com.saas.floweret.vo;

import java.util.Date;

import com.saas.auth.vo.Marriage;
import com.saas.pub.Scope;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="个人要求信息表")
public class MemberRequest {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="用户ID")
	private long customerId;

	@Field(desc="婚姻状况")
	private Marriage marriage;

	@Field(desc="年龄(公元年)")
	private Scope ageScope;

	@Field(desc="身高(cm)")
	private Scope heightScope;

	@Field(desc="体重(斤)")
	private Scope weightScope;

	@Field(desc="月薪（k）")
	private Scope salaryScope;

	@Field(desc="职业")
	private String[] occupations;

	@Field(desc="是否有车")
	private boolean owningCar;

	@Field(desc="是否有房")
	private boolean owningHouse;

	@Field(desc="修改日期")
	private Date updateDate;

	@Field(desc="创建日期")
	private Date createDate;

}

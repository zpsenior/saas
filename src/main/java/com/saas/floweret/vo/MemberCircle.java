package com.saas.floweret.vo;

import java.util.Date;
import java.util.List;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Join;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="个人展示表", incr="circleId")
public class MemberCircle {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="用户ID")
	private long customerId;

	@Field(isKey=true, desc="ID")
	private long circleId;

	@Field(desc="展现照片")
	private String[] imgs;
	
	@Field(desc="展示内容")
	private String description;
	
	@Field(desc="展示地址")
	private String location;
	
	@Field(desc="是否展示")
	private boolean showing;

	@Field(desc="创建日期")
	private Date createDate;
	
	@Join(request="queryMyReviewsList", params= {"tenantId", "customerId", "circleId"})
	private List<MemberCircleReview> reviews;
}

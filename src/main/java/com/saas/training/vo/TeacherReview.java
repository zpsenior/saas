package com.saas.training.vo;

import java.util.Date;

import com.saas.auth.vo.Customer;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Join;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="教师评价表", incr="reviewId")
public class TeacherReview {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="教师")
	private long staffId;

	@Field(isKey=true, desc="评价ID")
	private long reviewId;

	@Field(desc="评价用户")
	private long customerId;

	@Field(desc="评价内容", len=300)
	private String content;

	@Field(desc="状态")
	private boolean status;

	@Field(desc="修改日期")
	private Date updateDate;

	@Field(desc="创建日期")
	private Date createDate;
	
	@Join(request = "QueryCustomer.getCustomer", params = { "tenantId", "customerId" })
	private Customer commentator;

}

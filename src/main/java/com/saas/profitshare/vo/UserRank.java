package com.saas.profitshare.vo;

import java.util.Date;
import java.util.List;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Join;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="用户级别表")
public class UserRank {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="客户ID")
	private long customerId;

	@Field(desc="介绍人")
	private long introducer;

	@Field(desc="分润比例")
	private int sharePercent;

	@Field(desc="用户级别")
	private int level;

	@Field(desc="创建日期")
	private Date createDate;
	
	@Join(request="QueryProfitShare.queryReferenceList", params= {"customerId"})
	public List<UserRank> references;
}

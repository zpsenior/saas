package com.saas.goods.vo;


import java.util.Date;

import com.saas.auth.vo.Gender;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="收货地址表", incr="addressId")
public class PostAddress {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="客户ID")
	private String customerId;

	@Field(isKey=true, desc="地址ID")
	private long addressId;

	@Field(desc="收件人")
	private String recipient;
	
	@Field(desc="性别")
	private Gender gender;

	@Field(desc="联系电话")
	private String mobile;

	@Field(desc="省")
	private String province;

	@Field(desc="地市", len=50)
	private String city;

	@Field(desc="区县", len=50)
	private String county;

	@Field(desc="详细地址", len=100)
	private String detail;

	@Field(desc="创建日期")
	private Date createDate;

}

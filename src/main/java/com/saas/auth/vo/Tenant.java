package com.saas.auth.vo;

import java.util.Date;
import java.util.List;


import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Join;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="租户表")
public class Tenant {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(desc="租户名称", len=50)
	private String tenantName;

	@Field(desc="机构代码", len=30)
	private String license;

	@Field(desc="机构法人姓名")
	private String legalPerson;

	@Field(desc="机构法人身份证号")
	private String legalPersonId;

	@Field(desc="联系电话")
	private String mobileno;

	@Field(desc="注册地址", len=100)
	private String address;

	@Field(desc="修改日期")
	private Date updateDate;

	@Field(desc="创建日期")
	private Date createDate;

	@Field(desc="批准日期")
	private Date approvalDate;

	@Join(request = "QueryStaff.queryStaffList", params = { "tenantId" }, map = { "managers" })
	private List<TenantStaff> staffs;

	@Join(request = "QueryCustomer.queryCustomerList", params = { "tenantId" })
	private List<Customer> customer;
}

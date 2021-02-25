package com.saas.goods.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="物流信息表")
public class ExpressCompany {

	@Field(isKey=true, desc="公司编码")
	private String ExpressCompanyId;
	
	@Field
	private boolean showing;

	@Field(desc="修改日期")
	private Date updateDate;

	@Field(desc="创建日期")
	private Date createDate;
}

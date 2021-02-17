package com.saas.goods.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="商品收藏表")
public class GoodsFavorite {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="客户ID")
	private String customerId;

	@Field(isKey=true, desc="商品ID")
	private String goodsId;

	@Field(desc="创建日期")
	private Date createDate;

}

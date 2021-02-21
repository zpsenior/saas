package com.saas.goods.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="商品统计表")
public class GoodsDaily {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="商品ID")
	private long goodsId;

	@Field(isKey=true, desc="交易日期")
	private int tradeDate;

	@Field(desc="浏览次数")
	private int browseCount;

	@Field(desc="销售金额")
	private long saleAmount;

	@Field(desc="销售量")
	private int saleCount;

	@Field(desc="评论数量")
	private int reviewCount;

	@Field(desc="收藏数量")
	private int favoriteCount;

	@Field(desc="创建日期")
	private Date createDate;
	
}

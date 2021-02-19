package com.saas.goods.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="商品评价表", incr="reviewId")
public class GoodsReview {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="商品ID")
	private long goodsId;

	@Field(isKey=true, desc="商品评价ID")
	private long reviewId;

	@Field(desc="客户ID")
	private String customerId;

	@Field(desc="订单ID")
	private long orderId;

	@Field(desc="订单明细ID")
	private long orderItemId;
	
	@Field(desc="评价得分")
	private int score;
	
	@Field(desc="评价内容", len=500)
	private String content;
	
	@Field(desc="评价图片", len=500)
	private String[] imgs;

	@Field(desc="创建日期")
	private Date createDate;

}

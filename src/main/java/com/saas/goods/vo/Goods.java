package com.saas.goods.vo;

import java.util.Date;
import java.util.List;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Join;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="商品信息表", incr="goodsId")
public class Goods {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="商品ID")
	private long goodsId;

	@Field(desc="商品名称", len=100)
	private String title;

	@Field(desc="商品描述", len=500)
	private String description;

	@Field(desc="商品属性", len=100)
	private String property;

	@Field(desc="商品图片", len=300)
	private String[] imgs;
	
	@Field(desc="商品价格")
	private long price;
	
	@Field(desc="商品折扣")
	private int discount;

	@Field(desc="商品系列ID")
	private long parentId;

	@Field(desc="商品类别")
	private long categoryId;
	
	@Field(desc="商品供应商")
	private long provider;

	@Field(desc="是否实体商品")
	private boolean entity;

	@Field(desc="商品状态")
	private GoodsStatus status;

	@Field(desc="修改日期")
	private Date updateDate;

	@Field(desc="创建日期")
	private Date createDate;
	
	@Join(request = "QueryGoods.queryChildGoodsList", params = { "goodsId" })
	private List<Goods> children;
	
	@Join(request = "QueryGoods.queryGoodsReviewList", params = { "goodsId" })
	private List<GoodsReview> reviews;
	
}

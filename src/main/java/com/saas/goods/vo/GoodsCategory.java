package com.saas.goods.vo;

import java.util.Date;
import java.util.List;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Join;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="商品类别表")
public class GoodsCategory {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="类别ID")
	private String categoryId;

	@Field(desc="类别名称")
	private String name;
	
	@Field(desc="类别图片")
    private String img;
	
	@Field(desc="类别排序")
    private int sort;

	@Field(desc="创建日期")
	private Date createDate;


	@Join(bind = "queryGoodsList", params = { "tenantId", "categoryId" })
	private List<Goods> goodsList;

}

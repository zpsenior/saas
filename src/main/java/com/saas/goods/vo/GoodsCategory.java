package com.saas.goods.vo;

import java.util.Date;
import java.util.List;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Join;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="商品类别表", incr="categoryId")
public class GoodsCategory {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="类别ID")
	private long categoryId;

	@Field(desc="类别名称")
	private String name;
	
	@Field(desc="类别图片", len=100)
    private String img;

	@Field(desc="父类别ID")
	private long parentId;
	
	@Field(desc="类别排序")
    private int sort;

	@Field(desc="修改日期")
	private Date updateDate;

	@Field(desc="创建日期")
	private Date createDate;
	
	@Join(request = "QueryGoods.queryChildCategoryList", params = { "tenantId", "categoryId" })
	private List<Goods> children;


	@Join(request = "QueryGoods.queryGoodsList", params = { "tenantId", "categoryId" })
	private List<Goods> goodsList;

}

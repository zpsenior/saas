package com.saas.goods.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.goods.dao.DAOGoods;
import com.saas.goods.dao.DAOGoodsCategory;
import com.saas.goods.dao.DAOGoodsDaily;
import com.saas.goods.dao.DAOGoodsReview;
import com.saas.goods.request.QueryGoodsDailyParam;
import com.saas.goods.request.QueryGoodsParam;
import com.saas.goods.request.QueryGoodsReviewParam;
import com.saas.goods.vo.Goods;
import com.saas.goods.vo.GoodsCategory;
import com.saas.goods.vo.GoodsDaily;
import com.saas.goods.vo.GoodsReview;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("QueryGoods")
public class QueryGoods {
	
	@Autowired
	private DAOGoodsCategory category;
	
	@Autowired
	private DAOGoods goods;
	
	@Autowired
	private DAOGoodsReview reviews;
	
	@Autowired
	private DAOGoodsDaily goodsdaily;
	

	@Field("categories")
	public List<GoodsCategory> queryGoodsCategoryList(@Var("tenantId") String tenantId)throws Exception{
		QueryGoodsParam params = new QueryGoodsParam();
		params.setTenantId(tenantId);
		return category.queryGoodsCategoryList(params);
	}

	@Field("category")
	public GoodsCategory getGoodsCategory(@Var("tenantId") String tenantId, @Var("categoryId") long categoryId)throws Exception{
		GoodsCategory params = new GoodsCategory();
		params.setTenantId(tenantId);
		params.setCategoryId(categoryId);
		return category.getGoodsCategory(params);
	}

	@Field("goodsList")
	public List<Goods> queryGoodsList(@Var("tenantId") String tenantId)throws Exception{
		QueryGoodsParam params = new QueryGoodsParam();
		params.setTenantId(tenantId);
		params.setShowParent(true);
		return goods.queryGoodsList(params);
	}

	@Field("goodsChildList")
	public List<Goods> queryGoodsChildList(@Var("tenantId") String tenantId, @Var("parentId") long parentId)throws Exception{
		Goods params = new Goods();
		params.setTenantId(tenantId);
		params.setParentId(parentId);
		return goods.queryGoodsChildList(params);
	}

	@Field("goods")
	public Goods getGoods(@Var("tenantId") String tenantId, @Var("goodsId") long goodsId)throws Exception{
		Goods params = new Goods();
		params.setTenantId(tenantId);
		params.setGoodsId(goodsId);
		return goods.getGoods(params);
	}

	@Field("goodsReviews")
	public List<GoodsReview> queryGoodsReviewList(@Var("tenantId") String tenantId, @Var("goodsId") long goodsId)throws Exception{
		QueryGoodsReviewParam params = new QueryGoodsReviewParam();
		params.setTenantId(tenantId);
		params.setGoodsId(goodsId);
		return reviews.queryGoodsReviewList(params);
	}

	@Field("dailies")
	public List<GoodsDaily> queryGoodsDailyList(@Var("tenantId") String tenantId, @Var("goodsId") long goodsId)throws Exception{
		QueryGoodsDailyParam params = new QueryGoodsDailyParam();
		params.setTenantId(tenantId);
		params.setGoodsId(goodsId);
		return goodsdaily.queryGoodsDailyList(params);
	}
}

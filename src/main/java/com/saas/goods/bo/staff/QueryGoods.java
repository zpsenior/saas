package com.saas.goods.bo.staff;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.session.TenantStaffSession;
import com.saas.goods.dao.DAOGoods;
import com.saas.goods.dao.DAOGoodsCategory;
import com.saas.goods.dao.DAOGoodsReview;
import com.saas.goods.request.QueryGoodsParam;
import com.saas.goods.request.QueryGoodsReviewParam;
import com.saas.goods.vo.Goods;
import com.saas.goods.vo.GoodsCategory;
import com.saas.goods.vo.GoodsReview;
import com.saas.pub.BOBase;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("Staff.QueryGoods")
public class QueryGoods extends BOBase {
	
	@Autowired
	private DAOGoodsCategory category;
	
	@Autowired
	private DAOGoods goods;
	
	@Autowired
	private DAOGoodsReview reviews;

	@Field("categories")
	public List<GoodsCategory> queryGoodsCategoryList()throws Exception{
		TenantStaffSession session = getStaffSession();
		String tenantId = session.getTenantId();
		return category.queryCategoryList(tenantId);
	}
	
	@Field("category")
	public GoodsCategory getGoodsCategory(@Var("categoryId") long categoryId)throws Exception{
		GoodsCategory params = new GoodsCategory();
		TenantStaffSession session = getStaffSession();
		params.setTenantId(session.getTenantId());
		params.setCategoryId(categoryId);
		return category.getCategory(params);
	}
	
	@Field("goodsList")
	public List<Goods> queryGoodsList(@Var("params") QueryGoodsParam params)throws Exception{
		TenantStaffSession session = getStaffSession();
		params.setTenantId(session.getTenantId());
		return goods.searchGoodsList(params);
	}

	@Field("goods")
	public Goods getGoods(@Var("goodsId") long goodsId)throws Exception{
		Goods params = new Goods();
		TenantStaffSession session = getStaffSession();
		params.setTenantId(session.getTenantId());
		params.setGoodsId(goodsId);
		return goods.getGoods(params);
	}

	@Field("goodsReviews")
	public List<GoodsReview> queryGoodsReviewList(@Var("params") QueryGoodsReviewParam params)throws Exception{
		TenantStaffSession session = getStaffSession();
		params.setTenantId(session.getTenantId());
		return reviews.queryGoodsReviewList(params);
	}

	@Field("goodsReview")
	public GoodsReview getGoodsReview(@Var("reviewId") long reviewId)throws Exception{
		GoodsReview params = new GoodsReview();
		TenantStaffSession session = getStaffSession();
		params.setTenantId(session.getTenantId());
		params.setReviewId(reviewId);
		return reviews.getGoodsReview(params);
	}
}

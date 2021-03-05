package com.saas.goods.bo.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.session.CustomerSession;
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
@Component("Customer.QueryGoods")
public class QueryGoods extends BOBase {
	
	@Autowired
	private DAOGoodsCategory category;
	
	@Autowired
	private DAOGoods goods;
	
	@Autowired
	private DAOGoodsReview reviews;
	

	@Field("searchGoods")
	public List<Goods> searchGoodsListByPage(@Var("params") QueryGoodsParam params)throws Exception{
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setShowParent(true);
		return goods.searchGoodsList(params);
	}

	@Field("categories")
	public List<GoodsCategory> queryCategoryList()throws Exception{
		CustomerSession session = getCustomerSession();
		String tenantId = session.getTenantId();
		return category.queryCategoryList(tenantId);
	}
	
	public List<GoodsCategory> queryChildCategoryList(@Var("categoryId") long categoryId)throws Exception{
		TenantStaffSession session = getStaffSession();
		String tenantId = session.getTenantId();
		return category.queryCategoryChildList(tenantId, categoryId);
	}

	@Field("goodsList")
	public List<Goods> queryGoodsListByPage(@Var("params") QueryGoodsParam params)throws Exception{
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		return goods.queryGoodsList(params);
	}

	public List<Goods> queryChildGoodsList(@Var("parentId") long parentId)throws Exception{
		CustomerSession session = getCustomerSession();
		String tenantId = session.getTenantId();
		return goods.queryChildGoodsList(tenantId, parentId);
	}

	@Field("goods")
	public Goods getGoods(@Var("goodsId") long goodsId)throws Exception{
		Goods params = new Goods();
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setGoodsId(goodsId);
		return goods.getGoods(params);
	}

	@Field("goodsReviews")
	public List<GoodsReview> queryGoodsReviewListByPage(@Var("goodsId") long goodsId)throws Exception{
		QueryGoodsReviewParam params = new QueryGoodsReviewParam();
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setGoodsId(goodsId);
		return reviews.queryGoodsReviewList(params);
	}

	@Field("goodsReview")
	public GoodsReview getGoodsReview(@Var("reviewId") long reviewId)throws Exception{
		GoodsReview params = new GoodsReview();
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setReviewId(reviewId);
		return reviews.getGoodsReview(params);
	}
}

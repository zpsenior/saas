package com.saas.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.saas.goods.request.QueryGoodsReviewParam;
import com.saas.goods.vo.GoodsReview;

public interface DAOGoodsReview {

	@Select({"select * from goods_review where tenant_id=#{tenantId}"})
	public List<GoodsReview> queryGoodsReviewList(QueryGoodsReviewParam param)throws Exception;

	@Select({"select * from goods_review where tenant_id=#{tenantId} and goods_id=#{goodsId}"})
	public GoodsReview getGoodsReview(GoodsReview review)throws Exception;

	public void addGoodsReview(GoodsReview review)throws Exception;
	
	
	public void updateGoodsReview(GoodsReview review)throws Exception;

}

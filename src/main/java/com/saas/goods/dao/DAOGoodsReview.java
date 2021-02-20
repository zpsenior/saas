package com.saas.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.saas.goods.request.QueryGoodsReviewParam;
import com.saas.goods.vo.GoodsReview;

public interface DAOGoodsReview {

	@Select({"select * from goods_review where tenant_id=#{tenantId}"})
	public List<GoodsReview> queryGoodsReviewList(QueryGoodsReviewParam param)throws Exception;

	@Select({"select * from goods_review where tenant_id=#{tenantId} and goods_id=#{goodsId}"})
	public GoodsReview getGoodsReview(GoodsReview review)throws Exception;

	@Insert({
		"insert into goods_review(",
		"     tenant_id,  goods_id,  customer_id,  order_id,  order_item_id,   score,   content,   imgs,  create_date",
		")values(",
		"   #{tenantId},#{goodsId},#{customerId},#{orderId},#{orderItemId},#{score},#{content},#{imgs},   now()   )"
		})
	public void addGoodsReview(GoodsReview review)throws Exception;
	
	@Update({
		"update goods_review",
		"<trim prefix='set' suffixOverrides=','>",
		"   <if test=' customerId != null'>customer_id=#{customerId}</if>",
		"   <if test=' orderId != null'>order_id=#{orderId}</if>",
		"   <if test=' orderItemId != null'>order_item_id=#{orderItemId}</if>",
		"   <if test=' score != null'>score=#{score}</if>",
		"   <if test=' content != null'>content=#{content}</if>",
		"   <if test=' imgs != null'>imgs=#{imgs}</if>",
		"   <if test='true'>update_date=now()</if>",
		"</trim>",
		"where tenant_id=#{tenantId}",
		"  and goods_id=#{goodsId}",
		"  and review_id=#{reviewId}"
		})
	public void updateGoodsReview(GoodsReview review)throws Exception;

}

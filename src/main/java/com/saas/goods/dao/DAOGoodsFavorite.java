package com.saas.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.saas.goods.request.QueryGoodsParam;
import com.saas.goods.vo.GoodsFavorite;

public interface DAOGoodsFavorite {

	@Select({"select * from goods_favorite where tenant_id=#{tenantId} and customer_id=#{customerId}"})
	public List<GoodsFavorite> queryGoodsFavoriteList(QueryGoodsParam param)throws Exception;

	@Select({"select * from goods_favorite where tenant_id=#{tenantId} and customer_id=#{customerId} and goods_id=#{goodsId}"})
	public GoodsFavorite getGoodsFavorite(GoodsFavorite favorite)throws Exception;

	
	public void addGoodsFavorite(GoodsFavorite favorite)throws Exception;
	
	
	public void updateGoodsFavorite(GoodsFavorite favorite)throws Exception;

}

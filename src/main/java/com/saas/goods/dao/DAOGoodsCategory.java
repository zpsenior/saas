package com.saas.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.saas.goods.request.QueryGoodsParam;
import com.saas.goods.vo.GoodsCategory;

public interface DAOGoodsCategory {

	@Select({"select * from goods_category where tenant_id=#{tenantId} order by sort"})
	public List<GoodsCategory> queryGoodsCategoryList(QueryGoodsParam param)throws Exception;

	@Select({"select * from goods_category where tenant_id=#{tenantId} and category_id=#{categoryId}"})
	public GoodsCategory getGoodsCategory(GoodsCategory category)throws Exception;

	public void addGoodsCategory(GoodsCategory category)throws Exception;
	
	
	public void updateGoodsCategory(GoodsCategory category)throws Exception;

}

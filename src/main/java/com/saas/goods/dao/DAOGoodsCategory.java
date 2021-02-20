package com.saas.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.saas.goods.request.QueryGoodsParam;
import com.saas.goods.vo.GoodsCategory;

public interface DAOGoodsCategory {

	@Select({"select * from goods_category where tenant_id=#{tenantId} order by sort"})
	public List<GoodsCategory> queryGoodsCategoryList(QueryGoodsParam param)throws Exception;

	@Select({"select * from goods_category where tenant_id=#{tenantId} and category_id=#{categoryId}"})
	public GoodsCategory getGoodsCategory(GoodsCategory category)throws Exception;

	@Insert({
		"insert into goods_category(",
		"     tenant_id,   name,   img,   sort,  create_date",
		")values(",
		"   #{tenantId},#{name},#{img},#{sort},   now()   )"
		})
	public void addGoodsCategory(GoodsCategory category)throws Exception;
	
	@Update({
		"update goods_category",
		"<trim prefix='set' suffixOverrides=','>",
		"   <if test=' name != null'>name=#{name}</if>",
		"   <if test=' img != null'>img=#{img}</if>",
		"   <if test=' sort != null'>sort=#{sort}</if>",
		"   <if test='true'>update_date=now()</if>",
		"</trim>",
		"where tenant_id=#{tenantId}",
		"  and category_id=#{categoryId}"
		})

	public void updateGoodsCategory(GoodsCategory category)throws Exception;

}

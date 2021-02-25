package com.saas.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.saas.goods.vo.GoodsCategory;

@Mapper
public interface DAOGoodsCategory {

	@Select({"select * from goods_category where tenant_id=#{tenantId} and parent_id=#{categoryId} order by sort"})
	public List<GoodsCategory> queryChildCategoryList(@Param("tenantId") String tenantId, @Param("categoryId") long categoryId)throws Exception;

	@Select({"select * from goods_category where tenant_id=#{id} and parent_id is null order by sort"})
	public List<GoodsCategory> queryGoodsCategoryList(String tenantId)throws Exception;

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
		"<script>",
		"update goods_category",
		"<trim prefix='set' suffixOverrides=','>",
		"   <if test=' name != null'>name=#{name}</if>",
		"   <if test=' img != null'>img=#{img}</if>",
		"   <if test=' sort != null'>sort=#{sort}</if>",
		"   <if test='true'>update_date=now()</if>",
		"</trim>",
		"where tenant_id=#{tenantId}",
		"  and category_id=#{categoryId}",
		"</script>"
		})

	public void updateGoodsCategory(GoodsCategory category)throws Exception;


}

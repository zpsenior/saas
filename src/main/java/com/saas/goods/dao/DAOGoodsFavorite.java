package com.saas.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.saas.goods.request.QueryOrderParam;
import com.saas.goods.vo.GoodsFavorite;

@Mapper
public interface DAOGoodsFavorite {

	@Select({"select * from goods_favorite where tenant_id=#{tenantId} and customer_id=#{customerId}"})
	public List<GoodsFavorite> queryGoodsFavoriteList(QueryOrderParam param)throws Exception;

	@Select({"select * from goods_favorite where tenant_id=#{tenantId} and customer_id=#{customerId} and goods_id=#{goodsId}"})
	public GoodsFavorite getGoodsFavorite(GoodsFavorite favorite)throws Exception;

	@Insert({
		"insert into goods_favorite(",
		"     tenant_id,  customer_id,  goods_id,  create_date",
		")values(",
		"   #{tenantId},#{customerId},#{goodsId},   now()   )"
		})
	public void addGoodsFavorite(GoodsFavorite favorite)throws Exception;
	
	@Update({
		"<script>",
		"update goods_favorite",
		"<trim prefix='set' suffixOverrides=','>",
		"   <if test='true'>update_date=now()</if>",
		"</trim>",
		"where tenant_id=#{tenantId}",
		"  and customer_id=#{customerId}",
		"  and goods_id=#{goodsId}",
		"</script>"
		})
	public void updateGoodsFavorite(GoodsFavorite favorite)throws Exception;
	
	@Delete({"delete from goods_favorite where tenant_id=#{tenantId} and customer_id=#{customerId} and goods_id=#{goodsId}"})
	public void removeGoodsFavorite(GoodsFavorite favorite)throws Exception;

}

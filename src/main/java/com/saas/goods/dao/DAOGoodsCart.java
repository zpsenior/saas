package com.saas.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.saas.goods.request.QueryOrderParam;
import com.saas.goods.vo.GoodsCart;

@Mapper
public interface DAOGoodsCart {
	
	@Select({"select * from goods_cart where tenant_id=#{tenantId} and customer_id=#{customerId}"})
	public List<GoodsCart> queryAllMyGoodsCartList(@Param("tenantId") String tenantId, @Param("customerId") long customerId)throws Exception;
	
	@Select({"select * from goods_cart where tenant_id=#{tenantId} and customer_id=#{customerId}"})
	public List<GoodsCart> queryMyGoodsCartList(@Param("params") QueryOrderParam params)throws Exception;

	@Select({"select * from goods_cart where tenant_id=#{tenantId} and customer_id=#{customerId} and goods_id=#{goodsId}"})
	public GoodsCart getGoodsCart (GoodsCart cart)throws Exception;

	@Insert({
		"insert into goods_cart(",
		"     tenant_id,  goods_id,  customer_id,   count,  create_date",
		")values(",
		"   #{tenantId},#{goodsId},#{customerId},#{count},   now()   )"
		})
	public void addGoodsCart(GoodsCart cart)throws Exception;
	
	@Update({
		"<script>",
		"update goods_cart",
		"<trim prefix='set' suffixOverrides=','>",
		"   <if test=' count != null'>count=#{count}</if>",
		"   <if test='true'>update_date=now()</if>",
		"</trim>",
		"where tenant_id=#{tenantId}",
		"  and goods_id=#{goodsId}",
		"  and customer_id=#{customerId}",
		"</script>"
		})
	public void updateGoodsCart(GoodsCart cart)throws Exception;
	
	@Select({"delete from goods_cart where tenant_id=#{tenantId} and customer_id=#{customerId} and goods_id=#{goodsId}"})
	public void removeGoodsCart(GoodsCart cart)throws Exception;


}

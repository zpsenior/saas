package com.saas.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.saas.goods.request.QueryOrderParam;
import com.saas.goods.vo.GoodsCart;

public interface DAOGoodsCart {

	@Select({"select * from goods_cart where tenant_id=#{tenantId} and customer_id=#{customerId}"})
	public List<GoodsCart> queryGoodsCartList(QueryOrderParam params)throws Exception;

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
		"update goods_cart",
		"<trim prefix='set' suffixOverrides=','>",
		"   <if test=' count != null'>count=#{count}</if>",
		"   <if test='true'>update_date=now()</if>",
		"</trim>",
		"where tenant_id=#{tenantId}",
		"  and goods_id=#{goodsId}",
		"  and customer_id=#{customerId}"
		})
	public void updateGoodsCart(GoodsCart cart)throws Exception;

}

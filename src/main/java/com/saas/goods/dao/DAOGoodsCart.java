package com.saas.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.saas.goods.request.QueryGoodsParam;
import com.saas.goods.vo.GoodsCart;

public interface DAOGoodsCart {

	@Select({"select * from goods_cart where tenant_id=#{tenantId} and customer_id=#{customerId}"})
	public List<GoodsCart> queryGoodsCartList(QueryGoodsParam param)throws Exception;

	@Select({"select * from goods_cart where tenant_id=#{tenantId} and customer_id=#{customerId} and goods_id=#{goodsId}"})
	public GoodsCart getGoodsCart (GoodsCart cart)throws Exception;

	
	public void addGoodsCart(GoodsCart cart)throws Exception;
	
	
	public void updateGoodsCart(GoodsCart cart)throws Exception;

}

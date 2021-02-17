package com.saas.goods.bo;

import java.util.List;

import org.springframework.stereotype.Component;

import com.saas.goods.request.QueryGoodsParam;
import com.saas.goods.vo.Goods;
import com.saas.goods.vo.GoodsCategory;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("QueryGoods")
public class QueryGoods {
	

	@Field("categories")
	public List<GoodsCategory> queryGoodsCategoryList(@Var("tenantId") String tenantId)throws Exception{
		return null;
	}

	@Field("category")
	public GoodsCategory getGoodsCategory(@Var("tenantId") String tenantId, @Var("categoryId") long categoryId)throws Exception{
		return null;
	}

	@Field("goodsList")
	public List<Goods> queryGoodsList(@Var("params") QueryGoodsParam params)throws Exception{
		return null;
	}

	@Field("goods")
	public Goods getGoods(@Var("tenantId") String tenantId, @Var("goodsId") long goodsId)throws Exception{
		return null;
	}


}

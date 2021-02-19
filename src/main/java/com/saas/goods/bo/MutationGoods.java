package com.saas.goods.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.goods.dao.DAOGoods;
import com.saas.goods.dao.DAOGoodsCart;
import com.saas.goods.dao.DAOGoodsFavorite;
import com.saas.goods.request.GoodsParam;
import com.saas.goods.vo.Goods;
import com.saas.goods.vo.GoodsCart;
import com.saas.goods.vo.GoodsFavorite;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("MutationGoods")
public class MutationGoods {
	
	@Autowired
	private DAOGoods goods;
	
	@Autowired
	private DAOGoodsCart goodsCart;
	
	@Autowired
	private DAOGoodsFavorite goodsFavorite;
	
	@Field("create")
	public boolean createGoods(@Var("params") Goods params)throws Exception{
		goods.addGoods(params);
		return true;
	}
	
	@Field("update")
	public boolean updateGoods(@Var("params") Goods params)throws Exception{
		goods.updateGoods(params);
		return true;
	}
	
	@Field("addCart")
	public boolean addGoodsCart(@Var("params") GoodsParam params)throws Exception{
		GoodsCart cart = new GoodsCart();
		cart.setTenantId(params.getTenantId());
		cart.setCustomerId(params.getCustomerId());
		cart.setGoodsId(params.getGoodsId());
		goodsCart.addGoodsCart(cart);
		return true;
	}
	
	@Field("removeCart")
	public boolean removeGoodsCart(@Var("params") GoodsParam params)throws Exception{
		GoodsCart cart = new GoodsCart();
		cart.setTenantId(params.getTenantId());
		cart.setCustomerId(params.getCustomerId());
		cart.setGoodsId(params.getGoodsId());
		return true;
	}
	
	@Field("addFavorite")
	public boolean addGoodsFavorite(@Var("params") GoodsParam params)throws Exception{
		GoodsFavorite favorite = new GoodsFavorite();
		favorite.setTenantId(params.getTenantId());
		favorite.setCustomerId(params.getCustomerId());
		favorite.setGoodsId(params.getGoodsId());
		goodsFavorite.addGoodsFavorite(favorite);
		return true;
	}
	
	@Field("removeFavorite")
	public boolean removeGoodsFavorite(@Var("params") GoodsParam params)throws Exception{
		GoodsFavorite favorite = new GoodsFavorite();
		favorite.setTenantId(params.getTenantId());
		favorite.setCustomerId(params.getCustomerId());
		favorite.setGoodsId(params.getGoodsId());
		goodsFavorite.removeGoodsFavorite(favorite);
		return true;
	}

}

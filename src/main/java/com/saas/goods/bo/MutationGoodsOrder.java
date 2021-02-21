package com.saas.goods.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.goods.dao.DAOGoodsCart;
import com.saas.goods.dao.DAOGoodsFavorite;
import com.saas.goods.dao.DAOOrder;
import com.saas.goods.request.OrderParam;
import com.saas.goods.vo.GoodsCart;
import com.saas.goods.vo.GoodsFavorite;
import com.saas.goods.vo.Order;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("MutationGoodsOrder")
public class MutationGoodsOrder {
	
	@Autowired
	private DAOOrder goodsOrder;
	
	@Autowired
	private DAOGoodsCart goodsCart;
	
	@Autowired
	private DAOGoodsFavorite goodsFavorite;

	@Field
	public boolean buildOrder(@Var("params") OrderParam params)throws Exception{
		Order order = new Order();
		//???
		goodsOrder.updateOrder(order);
		return true;
	}
	
	@Field
	public boolean payOrder(@Var("params") OrderParam params)throws Exception{
		Order order = new Order();
		//???
		goodsOrder.updateOrder(order);
		return true;
	}
	
	@Field("addCart")
	public boolean addGoodsCart(@Var("tenantId") String tenantId, @Var("customerId") long customerId, @Var("goodsId") long goodsId)throws Exception{
		GoodsCart cart = new GoodsCart();
		cart.setTenantId(tenantId);
		cart.setCustomerId(customerId);
		cart.setGoodsId(goodsId);
		goodsCart.addGoodsCart(cart);
		return true;
	}
	
	@Field("removeCart")
	public boolean removeGoodsCart(@Var("tenantId") String tenantId, @Var("customerId") long customerId, @Var("goodsId") long goodsId)throws Exception{
		GoodsCart cart = new GoodsCart();
		cart.setTenantId(tenantId);
		cart.setCustomerId(customerId);
		cart.setGoodsId(goodsId);
		goodsCart.removeGoodsCart(cart);
		return true;
	}
	
	@Field("addFavorite")
	public boolean addGoodsFavorite(@Var("tenantId") String tenantId, @Var("customerId") long customerId, @Var("goodsId") long goodsId)throws Exception{
		GoodsFavorite favorite = new GoodsFavorite();
		favorite.setTenantId(tenantId);
		favorite.setCustomerId(customerId);
		favorite.setGoodsId(goodsId);
		goodsFavorite.addGoodsFavorite(favorite);
		return true;
	}
	
	@Field("removeFavorite")
	public boolean removeGoodsFavorite(@Var("tenantId") String tenantId, @Var("customerId") long customerId, @Var("goodsId") long goodsId)throws Exception{
		GoodsFavorite favorite = new GoodsFavorite();
		favorite.setTenantId(tenantId);
		favorite.setCustomerId(customerId);
		favorite.setGoodsId(goodsId);
		goodsFavorite.removeGoodsFavorite(favorite);
		return true;
	}

}

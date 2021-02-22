package com.saas.goods.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.goods.dao.DAOGoodsCart;
import com.saas.goods.dao.DAOGoodsFavorite;
import com.saas.goods.dao.DAOOrder;
import com.saas.goods.dao.DAOOrderItem;
import com.saas.goods.request.QueryOrderParam;
import com.saas.goods.vo.GoodsCart;
import com.saas.goods.vo.GoodsFavorite;
import com.saas.goods.vo.Order;
import com.saas.goods.vo.OrderItem;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("QueryGoodsOrder")
public class QueryGoodsOrder {

	@Autowired
	private DAOGoodsFavorite goodsFavorite;

	@Autowired
	private DAOGoodsCart goodsCart;

	@Autowired
	private DAOOrder goodsOrder;

	@Autowired
	private DAOOrderItem goodsOrderItem;
	
	@Field("orders")
	public List<Order> queryOrderList(@Var("params") QueryOrderParam params)throws Exception{
		return goodsOrder.queryOrderList(params);
	}

	@Field("order")
	public Order getOrder(@Var("tenantId") String tenantId, @Var("orderId") long orderId)throws Exception{
		Order params = new Order();
		params.setTenantId(tenantId);
		params.setOrderId(orderId);
		return goodsOrder.getOrder(params);
	}
	
	@Field("orderItems")
	public List<OrderItem> queryOrderItemList(@Var("params") QueryOrderParam params)throws Exception{
		return goodsOrderItem.queryOrderItemList(params);
	}

	/*@Field("orderItems")
	public List<OrderItem> queryOrderItemList(@Var("tenantId") String tenantId, @Var("orderId") long orderId)throws Exception{
		QueryOrderParam params = new QueryOrderParam();
		params.setTenantId(tenantId);
		params.setOrderId(orderId);
		return goodsOrderItem.queryOrderItemList(params);
	}*/

	@Field("orderItem")
	public OrderItem getOrderItem(@Var("tenantId") String tenantId, @Var("orderId") long orderId, @Var("itemId") long orderItemId)throws Exception{
		OrderItem params = new OrderItem();
		params.setTenantId(tenantId);
		params.setOrderId(orderId);
		params.setOrderItemId(orderItemId);
		return goodsOrderItem.getOrderItem(params);
	}

	@Field("carts")
	public List<GoodsCart> queryGoodsCartList(@Var("params") QueryOrderParam params)throws Exception{
		return goodsCart.queryGoodsCartList(params);
	}

	@Field("cart")
	public GoodsCart getGoodsCart(@Var("tenantId") String tenantId, @Var("customerId") long customerId, @Var("goodsId") long goodsId)throws Exception{
		GoodsCart params = new GoodsCart();
		params.setTenantId(tenantId);
		params.setCustomerId(customerId);
		params.setGoodsId(goodsId);
		return goodsCart.getGoodsCart(params);
	}

	@Field("favorites")
	public List<GoodsFavorite> queryGoodsFavoriteList(@Var("params") QueryOrderParam params)throws Exception{
		return goodsFavorite.queryGoodsFavoriteList(params);
	}

	@Field("favorite")
	public GoodsFavorite getGoodsFavorite(@Var("tenantId") String tenantId, @Var("customerId") long customerId, @Var("goodsId") long goodsId)throws Exception{
		GoodsFavorite params = new GoodsFavorite();
		params.setTenantId(tenantId);
		params.setCustomerId(customerId);
		params.setGoodsId(goodsId);
		return goodsFavorite.getGoodsFavorite(params);
	}

}

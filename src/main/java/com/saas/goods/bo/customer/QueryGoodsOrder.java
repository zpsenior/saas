package com.saas.goods.bo.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.session.CustomerSession;
import com.saas.goods.dao.DAOGoodsCart;
import com.saas.goods.dao.DAOGoodsFavorite;
import com.saas.goods.dao.DAOOrder;
import com.saas.goods.dao.DAOOrderItem;
import com.saas.goods.request.QueryOrderParam;
import com.saas.goods.vo.GoodsCart;
import com.saas.goods.vo.GoodsFavorite;
import com.saas.goods.vo.Order;
import com.saas.goods.vo.OrderItem;
import com.saas.pub.BOBase;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("Customer.QueryGoodsOrder")
public class QueryGoodsOrder extends BOBase {

	@Autowired
	private DAOGoodsFavorite goodsFavorite;

	@Autowired
	private DAOGoodsCart goodsCart;

	@Autowired
	private DAOOrder goodsOrder;

	@Autowired
	private DAOOrderItem goodsOrderItem;
	
	@Field("myOrders")
	public List<Order> queryMyOrderListByPage(@Var("params") QueryOrderParam params)throws Exception{
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setCustomerId(session.getCustomerId());
		return goodsOrder.queryOrderList(params);
	}

	@Field("myOrder")
	public Order getMyOrder(@Var("orderId") long orderId)throws Exception{
		CustomerSession session = getCustomerSession();
		Order params = new Order();
		params.setTenantId(session.getTenantId());
		params.setCustomerId(session.getCustomerId());
		params.setOrderId(orderId);
		return goodsOrder.getOrder(params);
	}

	public List<OrderItem> queryMyOrderItemList(@Var("orderId") long orderId)throws Exception{
		QueryOrderParam params = new QueryOrderParam();
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setCustomerId(session.getCustomerId());
		return goodsOrderItem.queryOrderItemList(params);
	}

	@Field("myOrderItem")
	public OrderItem getMyOrderItem(@Var("itemId") long orderItemId)throws Exception{
		CustomerSession session = getCustomerSession();
		OrderItem params = new OrderItem();
		params.setTenantId(session.getTenantId());
		params.setCustomerId(session.getCustomerId());
		params.setOrderItemId(orderItemId);
		return goodsOrderItem.getOrderItem(params);
	}

	@Field("myCarts")
	public List<GoodsCart> queryMyGoodsCartListByPage(@Var("params") QueryOrderParam params)throws Exception{
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setCustomerId(session.getCustomerId());
		return goodsCart.queryMyGoodsCartList(params);
	}

	@Field("myCart")
	public GoodsCart getMyGoodsCart(@Var("goodsId") long goodsId)throws Exception{
		CustomerSession session = getCustomerSession();
		GoodsCart params = new GoodsCart();
		params.setTenantId(session.getTenantId());
		params.setCustomerId(session.getCustomerId());
		return goodsCart.getGoodsCart(params);
	}

	@Field("myFavorites")
	public List<GoodsFavorite> queryGoodsFavoriteListByPage(@Var("params") QueryOrderParam params)throws Exception{
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setCustomerId(session.getCustomerId());
		return goodsFavorite.queryGoodsFavoriteList(params);
	}

	@Field("myFavorite")
	public GoodsFavorite getGoodsFavorite(@Var("goodsId") long goodsId)throws Exception{
		CustomerSession session = getCustomerSession();
		GoodsFavorite params = new GoodsFavorite();
		params.setTenantId(session.getTenantId());
		params.setCustomerId(session.getCustomerId());
		params.setGoodsId(goodsId);
		return goodsFavorite.getGoodsFavorite(params);
	}

}

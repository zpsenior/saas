package com.saas.goods.bo.customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.session.CustomerSession;
import com.saas.goods.dao.DAOGoods;
import com.saas.goods.dao.DAOGoodsCart;
import com.saas.goods.dao.DAOGoodsFavorite;
import com.saas.goods.dao.DAOOrder;
import com.saas.goods.dao.DAOOrderItem;
import com.saas.goods.dao.DAOPostAddress;
import com.saas.goods.vo.Goods;
import com.saas.goods.vo.GoodsCart;
import com.saas.goods.vo.GoodsFavorite;
import com.saas.goods.vo.Order;
import com.saas.goods.vo.OrderItem;
import com.saas.goods.vo.OrderStatus;
import com.saas.goods.vo.PayType;
import com.saas.goods.vo.PostAddress;
import com.saas.pub.BOBase;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("Customer.MutationGoodsOrder")
public class MutationGoodsOrder extends BOBase {
	
	@Autowired
	private DAOGoods goods;
	
	@Autowired
	private DAOOrder goodsOrder;
	
	@Autowired
	private DAOOrderItem goodsOrderItem;
	
	@Autowired
	private DAOGoodsCart goodsCart;
	
	@Autowired
	private DAOGoodsFavorite goodsFavorite;
	
	@Autowired
	private DAOPostAddress postAddress;
	
	@Field
	public Order buildOrderFromCart(@Var("addressId") long addressId)throws Exception{
		CustomerSession session = getCustomerSession();
		String tenantId = session.getTenantId();
		long customerId = session.getCustomerId();
		List<GoodsCart> carts = goodsCart.queryMyGoodsCartList(tenantId, customerId);
		if(carts == null || carts.size() <= 0) {
			throw new RuntimeException("empty goods carts!");
		}
		PostAddress address = new PostAddress();
		address.setTenantId(tenantId);
		address.setAddressId(addressId);
		address = postAddress.getPostAddress(address);
		if(address == null) {
			throw new RuntimeException("can not find address:" + addressId);
		}
		long totalAmount = 0;
		Goods params = new Goods();
		params.setTenantId(tenantId);
		Order order = new Order(address);
		order.setCustomerId(customerId);
		order.setStatus(OrderStatus.PREPAY);
		order.setPayType(PayType.wechatpay);
		List<OrderItem> items = new ArrayList<>();
		for(GoodsCart cart : carts) {
			params.setGoodsId(cart.getGoodsId());
			Goods gds = goods.getGoods(params);
			if(gds == null) {
				throw new RuntimeException("can not find goods:" + cart.getGoodsId());
			}
			OrderItem item = new OrderItem(gds, cart.getCount());
			item.setTenantId(order.getTenantId());
			item.setCustomerId(customerId);
			totalAmount += item.getAmount();
			items.add(item);
		}
		order.setTotalAmount(totalAmount);
		goodsOrder.addOrder(order);
		for(OrderItem oi : items) {
			oi.setOrderId(order.getOrderId());
			goodsOrderItem.addOrderItem(oi);
		}
		for(GoodsCart cart : carts) {
			goodsCart.removeGoodsCart(cart);
		}
		return order;
	}

	@Field
	public Order buildOrder(@Var("addressId") long addressId, @Var("goodsId") long goodsId, @Var("count") int count)throws Exception{
		CustomerSession session = getCustomerSession();
		String tenantId = session.getTenantId();
		long customerId = session.getCustomerId();

		PostAddress address = new PostAddress();
		address.setTenantId(tenantId);
		address.setAddressId(addressId);
		address = postAddress.getPostAddress(address);
		if(address == null) {
			throw new RuntimeException("can not find address:" + addressId);
		}
		Goods params = new Goods();
		params.setTenantId(tenantId);
		params.setGoodsId(goodsId);
		Goods gds = goods.getGoods(params);
		if(gds == null) {
			throw new RuntimeException("can not find goods:" + goodsId);
		}
		Order order = new Order(address);
		order.setTenantId(tenantId);
		order.setCustomerId(customerId);
		order.setStatus(OrderStatus.PREPAY);
		order.setPayType(PayType.wechatpay);
		OrderItem item = new OrderItem(gds, count);
		order.setTotalAmount(item.getAmount());
		item.setCustomerId(customerId);
		goodsOrder.addOrder(order);
		item.setOrderId(order.getOrderId());
		goodsOrderItem.addOrderItem(item);
		return order;
	}
	
	@Field
	public boolean payOrder(@Var("orderId") long orderId)throws Exception{
		CustomerSession session = getCustomerSession();
		String tenantId = session.getTenantId();
		long customerId = session.getCustomerId();
		Order params = new Order();
		params.setTenantId(tenantId);
		params.setCustomerId(customerId);
		params.setOrderId(orderId);
		params = goodsOrder.getOrder(params);
		if(params == null) {
			throw new RuntimeException("can not find order:" + orderId + " in tenant:" + tenantId);
		}
		Order order = new Order();
		order.setTenantId(tenantId);
		order.setOrderId(orderId);
		order.setStatus(OrderStatus.PAYED);
		order.setPayDate(new Date());
		goodsOrder.updateOrder(order);
		return true;
	}
	
	@Field("addCart")
	public boolean addGoodsCart(@Var("goodsId") long goodsId, @Var("count") int count)throws Exception{
		CustomerSession session = getCustomerSession();
		String tenantId = session.getTenantId();
		long customerId = session.getCustomerId();
		GoodsCart cart = new GoodsCart();
		cart.setTenantId(tenantId);
		cart.setCustomerId(customerId);
		cart.setGoodsId(goodsId);
		cart.setCount(count);
		goodsCart.addGoodsCart(cart);
		return true;
	}
	
	@Field("updateCart")
	public boolean updateGoodsCart(@Var("goodsId") long goodsId, @Var("count") int count)throws Exception{
		CustomerSession session = getCustomerSession();
		String tenantId = session.getTenantId();
		long customerId = session.getCustomerId();
		GoodsCart cart = new GoodsCart();
		cart.setTenantId(tenantId);
		cart.setCustomerId(customerId);
		cart.setGoodsId(goodsId);
		cart.setCount(count);
		goodsCart.updateGoodsCart(cart);
		return true;
	}
	
	@Field("removeCart")
	public boolean removeGoodsCart(@Var("goodsId") long goodsId)throws Exception{
		CustomerSession session = getCustomerSession();
		String tenantId = session.getTenantId();
		long customerId = session.getCustomerId();
		GoodsCart cart = new GoodsCart();
		cart.setTenantId(tenantId);
		cart.setCustomerId(customerId);
		cart.setGoodsId(goodsId);
		goodsCart.removeGoodsCart(cart);
		return true;
	}
	
	@Field("addFavorite")
	public boolean addGoodsFavorite(@Var("goodsId") long goodsId)throws Exception{
		CustomerSession session = getCustomerSession();
		String tenantId = session.getTenantId();
		long customerId = session.getCustomerId();
		GoodsFavorite favorite = new GoodsFavorite();
		favorite.setTenantId(tenantId);
		favorite.setCustomerId(customerId);
		favorite.setGoodsId(goodsId);
		goodsFavorite.addGoodsFavorite(favorite);
		return true;
	}
	
	@Field("removeFavorite")
	public boolean removeGoodsFavorite( @Var("goodsId") long goodsId)throws Exception{
		CustomerSession session = getCustomerSession();
		String tenantId = session.getTenantId();
		long customerId = session.getCustomerId();
		GoodsFavorite favorite = new GoodsFavorite();
		favorite.setTenantId(tenantId);
		favorite.setCustomerId(customerId);
		favorite.setGoodsId(goodsId);
		goodsFavorite.removeGoodsFavorite(favorite);
		return true;
	}

}

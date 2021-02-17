package com.saas.goods.bo;

import java.util.List;

import org.springframework.stereotype.Component;

import com.saas.goods.request.QueryOrderParam;
import com.saas.goods.vo.Order;
import com.saas.goods.vo.OrderItem;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("QueryGoodsOrder")
public class QueryGoodsOrder {

	@Field("orders")
	public List<Order> queryOrderList(@Var("params") QueryOrderParam params)throws Exception{
		return null;
	}

	@Field("order")
	public OrderItem getOrder(@Var("tenantId") String tenantId, @Var("orderId") long orderId)throws Exception{
		return null;
	}

	@Field("orderItems")
	public List<OrderItem> queryOrderItemList(@Var("params") QueryOrderParam params)throws Exception{
		return null;
	}

	@Field("orderItem")
	public OrderItem getOrderItem(@Var("tenantId") String tenantId, @Var("orderId") long orderId, @Var("itemId") long itemId)throws Exception{
		return null;
	}

}

package com.saas.goods.bo.staff;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.session.TenantStaffSession;
import com.saas.goods.dao.DAOOrder;
import com.saas.goods.dao.DAOOrderItem;
import com.saas.goods.request.QueryOrderParam;
import com.saas.goods.vo.Order;
import com.saas.goods.vo.OrderItem;
import com.saas.pub.BOBase;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("Staff.QueryGoodsOrder")
public class QueryGoodsOrder extends BOBase {

	@Autowired
	private DAOOrder goodsOrder;

	@Autowired
	private DAOOrderItem goodsOrderItem;
	
	@Field("orders")
	public List<Order> queryOrderList(@Var("params") QueryOrderParam params)throws Exception{

		TenantStaffSession session = getStaffSession();
		params.setTenantId(session.getTenantId());
		return goodsOrder.queryOrderList(params);
	}

	@Field("order")
	public Order getOrder(@Var("orderId") long orderId)throws Exception{

		TenantStaffSession session = getStaffSession();
		Order params = new Order();
		params.setTenantId(session.getTenantId());
		params.setOrderId(orderId);
		return goodsOrder.getOrder(params);
	}

	public List<OrderItem> queryOrderItemList(@Var("orderId") long orderId)throws Exception{
		QueryOrderParam params = new QueryOrderParam();

		TenantStaffSession session = getStaffSession();
		params.setTenantId(session.getTenantId());
		return goodsOrderItem.queryOrderItemList(params);
	}

	@Field("orderItem")
	public OrderItem getOrderItem(@Var("itemId") long orderItemId)throws Exception{

		TenantStaffSession session = getStaffSession();
		OrderItem params = new OrderItem();
		params.setTenantId(session.getTenantId());
		params.setOrderItemId(orderItemId);
		return goodsOrderItem.getOrderItem(params);
	}

}

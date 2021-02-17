package com.saas.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.saas.goods.request.QueryOrderParam;
import com.saas.goods.vo.OrderItem;

public interface DAOOrderItem {

	@Select({"select * from goods_order_item where tenant_id=#{tenantId} and order_id=#{orderId}"})
	public List<OrderItem> queryOrderItemList(QueryOrderParam param)throws Exception;

	@Select({"select * from goods_order_item where tenant_id=#{tenantId} and order_id=#{orderId} and item_id=#{itemId}"})
	public OrderItem getOrderItem(OrderItem item)throws Exception;

	public void addOrderItem(OrderItem item)throws Exception;
	
	
	public void updateOrderItem(OrderItem item)throws Exception;

}

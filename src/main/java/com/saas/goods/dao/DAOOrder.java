package com.saas.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.saas.goods.request.QueryOrderParam;
import com.saas.goods.vo.Order;

public interface DAOOrder {

	@Select({"select * from goods_order where tenant_id=#{tenantId}"})
	public List<Order> queryOrderList(QueryOrderParam param)throws Exception;

	@Select({"select * from goods_order where tenant_id=#{tenantId} and order_id=#{orderId}"})
	public Order getOrder(Order order)throws Exception;

	public void addOrder(Order order)throws Exception;
	
	
	public void updateOrder(Order order)throws Exception;

}

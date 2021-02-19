package com.saas.goods.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.goods.dao.DAOOrder;
import com.saas.goods.request.OrderParam;
import com.saas.goods.vo.Order;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("MutationGoodsOrder")
public class MutationGoodsOrder {
	
	@Autowired
	private DAOOrder goodsOrder;

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

}

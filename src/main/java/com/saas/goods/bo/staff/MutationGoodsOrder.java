package com.saas.goods.bo.staff;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.session.CustomerSession;
import com.saas.auth.session.TenantStaffSession;
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
@Component("Staff.MutationGoodsOrder")
public class MutationGoodsOrder extends BOBase {
	
	@Autowired
	private DAOOrder goodsOrder;
	
	@Autowired
	private DAOOrderItem goodsOrderItem;

	
	@Field
	public boolean processOrder(@Var("orderId") long orderId)throws Exception{
		TenantStaffSession session = getStaffSession();
		String tenantId = session.getTenantId();
		Order params = new Order();
		params.setTenantId(tenantId);
		params.setOrderId(orderId);
		params = goodsOrder.getOrder(params);
		if(params == null) {
			throw new RuntimeException("can not find order:" + orderId + " in tenant:" + tenantId);
		}
		Order order = new Order();
		order.setTenantId(tenantId);
		order.setOrderId(orderId);
		order.setStatus(OrderStatus.SENT);
		order.setSendDate(new Date());
		goodsOrder.updateOrder(order);
		return true;
	}


}

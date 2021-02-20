package com.saas.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.saas.goods.request.QueryOrderParam;
import com.saas.goods.vo.OrderItem;

@Mapper
public interface DAOOrderItem {

	@Select({"select * from goods_order_item where tenant_id=#{tenantId} and order_id=#{orderId}"})
	public List<OrderItem> queryOrderItemList(QueryOrderParam param)throws Exception;

	@Select({"select * from goods_order_item where tenant_id=#{tenantId} and order_id=#{orderId} and item_id=#{itemId}"})
	public OrderItem getOrderItem(OrderItem item)throws Exception;

	@Insert({
		"insert into goods_order_item(",
		"     tenant_id,  order_id,  goods_id,  serial_no,   count,   amount,  logistics_id,  create_date",
		")values(",
		"   #{tenantId},#{orderId},#{goodsId},#{serialNo},#{count},#{amount},#{logisticsId},   now()   )"
		})
	public void addOrderItem(OrderItem item)throws Exception;
	
	@Update({
		"<script>",
		"update goods_order_item",
		"<trim prefix='set' suffixOverrides=','>",
		"   <if test=' goodsId != null'>goods_id=#{goodsId}</if>",
		"   <if test=' serialNo != null'>serial_no=#{serialNo}</if>",
		"   <if test=' count != null'>count=#{count}</if>",
		"   <if test=' amount != null'>amount=#{amount}</if>",
		"   <if test=' logisticsId != null'>logistics_id=#{logisticsId}</if>",
		"   <if test='true'>update_date=now()</if>",
		"</trim>",
		"where tenant_id=#{tenantId}",
		"  and order_id=#{orderId}",
		"  and order_item_id=#{orderItemId}",
		"</script>"
		})
	public void updateOrderItem(OrderItem item)throws Exception;

}

package com.saas.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.saas.goods.request.QueryOrderParam;
import com.saas.goods.vo.Order;

@Mapper
public interface DAOOrder {

	@Select({"select * from goods_order where tenant_id=#{tenantId}"})
	public List<Order> queryOrderList(QueryOrderParam param)throws Exception;

	@Select({"select * from goods_order where tenant_id=#{tenantId} and order_id=#{orderId}"})
	public Order getOrder(Order order)throws Exception;

	@Insert({
		"insert into goods_order(",
		"     tenant_id,  customer_id,  total_amount,   status,   recipient,   gender,   mobile,   province,   city,   county,   detail,  pay_date,  create_date",
		")values(",
		"   #{tenantId},#{customerId},#{totalAmount},#{status},#{recipient},#{gender},#{mobile},#{province},#{city},#{county},#{detail},#{payDate},   now()   )"
		})
	public void addOrder(Order order)throws Exception;
	
	@Update({
		"<script>",
		"update goods_order",
		"<trim prefix='set' suffixOverrides=','>",
		"   <if test=' customerId != null'>customer_id=#{customerId}</if>",
		"   <if test=' totalAmount != null'>total_amount=#{totalAmount}</if>",
		"   <if test=' status != null'>status=#{status}</if>",
		"   <if test=' recipient != null'>recipient=#{recipient}</if>",
		"   <if test=' gender != null'>gender=#{gender}</if>",
		"   <if test=' mobile != null'>mobile=#{mobile}</if>",
		"   <if test=' province != null'>province=#{province}</if>",
		"   <if test=' city != null'>city=#{city}</if>",
		"   <if test=' county != null'>county=#{county}</if>",
		"   <if test=' detail != null'>detail=#{detail}</if>",
		"   <if test=' payDate != null'>pay_date=#{payDate}</if>",
		"   <if test='true'>update_date=now()</if>",
		"</trim>",
		"where tenant_id=#{tenantId}",
		"  and order_id=#{orderId}",
		"</script>"
		})
	public void updateOrder(Order order)throws Exception;

}

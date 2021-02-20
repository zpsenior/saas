package com.saas.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.saas.goods.request.QueryServiceParam;
import com.saas.goods.vo.ServiceItem;

@Mapper
public interface DAOServiceItem {

	@Select({"select * from goods_service_item where tenant_id=#{tenantId} and order_id=#{orderId}"})
	public List<ServiceItem> queryServiceItemList(QueryServiceParam param)throws Exception;

	@Select({"select * from goods_service_item where tenant_id=#{tenantId} and service_id=#{serviceId} and item_id=#{itemId}"})
	public ServiceItem getServiceItem(ServiceItem item)throws Exception;

	@Insert({
		"insert into goods_service_item(",
		"     tenant_id,  service_id,  staff_id,  customer_id,  begin_time,  end_time,  sign_in,   imgs,  create_date",
		")values(",
		"   #{tenantId},#{serviceId},#{staffId},#{customerId},#{beginTime},#{endTime},#{signIn},#{imgs},   now()   )"
		})
	public void addServiceItem(ServiceItem item)throws Exception;
	
	@Update({
		"<script>",
		"update goods_service_item",
		"<trim prefix='set' suffixOverrides=','>",
		"   <if test=' staffId != null'>staff_id=#{staffId}</if>",
		"   <if test=' customerId != null'>customer_id=#{customerId}</if>",
		"   <if test=' beginTime != null'>begin_time=#{beginTime}</if>",
		"   <if test=' endTime != null'>end_time=#{endTime}</if>",
		"   <if test=' signIn != null'>sign_in=#{signIn}</if>",
		"   <if test=' imgs != null'>imgs=#{imgs}</if>",
		"   <if test='true'>update_date=now()</if>",
		"</trim>",
		"where tenant_id=#{tenantId}",
		"  and service_id=#{serviceId}",
		"  and service_item_id=#{serviceItemId}",
		"</script>"
		})
	public void updateServiceItem(ServiceItem item)throws Exception;

}

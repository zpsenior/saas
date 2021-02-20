package com.saas.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.saas.goods.request.QueryServiceParam;
import com.saas.goods.vo.Service;

@Mapper
public interface DAOService {

	@Select({"select * from goods_service where tenant_id=#{tenantId}"})
	public List<Service> queryServiceList(QueryServiceParam param)throws Exception;

	@Select({"select * from goods_service where tenant_id=#{tenantId} and service_id=#{serviceId}"})
	public Service getService(Service service)throws Exception;

	@Insert({
		"insert into goods_service(",
		"     tenant_id,  customer_id,  staff_id,  goods_id,  order_id,  order_item_id,  spent_count,  total_count,  create_date",
		")values(",
		"   #{tenantId},#{customerId},#{staffId},#{goodsId},#{orderId},#{orderItemId},#{spentCount},#{totalCount},   now()   )"
		})
	public void addService(Service service)throws Exception;
	
	@Update({
		"<script>",
		"update goods_service",
		"<trim prefix='set' suffixOverrides=','>",
		"   <if test=' customerId != null'>customer_id=#{customerId}</if>",
		"   <if test=' staffId != null'>staff_id=#{staffId}</if>",
		"   <if test=' goodsId != null'>goods_id=#{goodsId}</if>",
		"   <if test=' orderId != null'>order_id=#{orderId}</if>",
		"   <if test=' orderItemId != null'>order_item_id=#{orderItemId}</if>",
		"   <if test=' spentCount != null'>spent_count=#{spentCount}</if>",
		"   <if test=' totalCount != null'>total_count=#{totalCount}</if>",
		"   <if test='true'>update_date=now()</if>",
		"</trim>",
		"where tenant_id=#{tenantId}",
		"  and service_id=#{serviceId}",
		"</script>"
		})
	public void updateService(Service service)throws Exception;

}

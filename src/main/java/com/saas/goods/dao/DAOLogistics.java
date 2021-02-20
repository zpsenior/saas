package com.saas.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.saas.goods.request.QueryLogisticsParam;
import com.saas.goods.vo.Logistics;

public interface DAOLogistics {
	
	@Select("select * from goods_logistics where tenant_id=#{tenantId} order by create_date desc")
	public List<Logistics> queryLogisticsList(QueryLogisticsParam params)throws Exception;
	
	@Select("select * from goods_logistics where tenant_id=#{tenantId} and logistics_id=#{logisticsId}")
	public Logistics getLogistics(Logistics params)throws Exception;
	
	@Insert({
		"insert into goods_logistics(",
		"     tenant_id,  order_id,  customer_id,  express_id,  express_no,  send_out_date,   recipient,   gender,   mobile,   province,   city,   county,   detail,  create_date",
		")values(",
		"   #{tenantId},#{orderId},#{customerId},#{expressId},#{expressNo},#{sendOutDate},#{recipient},#{gender},#{mobile},#{province},#{city},#{county},#{detail},   now()   )"
		})
	public void addLogistics(Logistics params)throws Exception;
	
	@Update({
		"update goods_logistics",
		"<trim prefix='set' suffixOverrides=','>",
		"   <if test=' orderId != null'>order_id=#{orderId}</if>",
		"   <if test=' customerId != null'>customer_id=#{customerId}</if>",
		"   <if test=' expressId != null'>express_id=#{expressId}</if>",
		"   <if test=' expressNo != null'>express_no=#{expressNo}</if>",
		"   <if test=' sendOutDate != null'>send_out_date=#{sendOutDate}</if>",
		"   <if test=' recipient != null'>recipient=#{recipient}</if>",
		"   <if test=' gender != null'>gender=#{gender}</if>",
		"   <if test=' mobile != null'>mobile=#{mobile}</if>",
		"   <if test=' province != null'>province=#{province}</if>",
		"   <if test=' city != null'>city=#{city}</if>",
		"   <if test=' county != null'>county=#{county}</if>",
		"   <if test=' detail != null'>detail=#{detail}</if>",
		"   <if test='true'>update_date=now()</if>",
		"</trim>",
		"where tenant_id=#{tenantId}",
		"  and logistics_id=#{logisticsId}"
		})
	public void updateLogistics(Logistics params)throws Exception;

}

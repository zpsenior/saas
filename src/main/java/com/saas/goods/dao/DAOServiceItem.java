package com.saas.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.saas.goods.request.QueryServiceParam;
import com.saas.goods.vo.ServiceItem;

public interface DAOServiceItem {

	@Select({"select * from goods_service_item where tenant_id=#{tenantId} and order_id=#{orderId}"})
	public List<ServiceItem> queryServiceItemList(QueryServiceParam param)throws Exception;

	@Select({"select * from goods_service_item where tenant_id=#{tenantId} and service_id=#{serviceId} and item_id=#{itemId}"})
	public ServiceItem getServiceItem(ServiceItem item)throws Exception;

	public void addServiceItem(ServiceItem item)throws Exception;
	
	
	public void updateServiceItem(ServiceItem item)throws Exception;

}

package com.saas.goods.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.goods.dao.DAOService;
import com.saas.goods.dao.DAOServiceItem;
import com.saas.goods.request.QueryServiceParam;
import com.saas.goods.vo.Service;
import com.saas.goods.vo.ServiceItem;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("QueryGoodsService")
public class QueryGoodsService {
	
	@Autowired
	private DAOService service;
	
	@Autowired
	private DAOServiceItem serviceItem;

	@Field("services")
	public List<Service> queryServiceList(@Var("params") QueryServiceParam params)throws Exception{
		return service.queryServiceList(params);
	}

	@Field("service")
	public Service getService(@Var("tenantId") String tenantId, @Var("serviceId") Long serviceId)throws Exception{
		Service params = new Service();
		params.setTenantId(tenantId);
		params.setServiceId(serviceId);
		return service.getService(params);
	}

	@Field("serviceItems")
	public List<ServiceItem> queryServiceItemList(@Var("params") QueryServiceParam params)throws Exception{
		return serviceItem.queryServiceItemList(params);
	}

	@Field("serviceItem")
	public ServiceItem getServiceItem(@Var("tenantId") String tenantId, @Var("serviceId") Long serviceId, @Var("itemId") long itemId)throws Exception{
		ServiceItem params = new ServiceItem();
		params.setTenantId(tenantId);
		params.setServiceId(serviceId);
		params.setServiceItemId(itemId);
		return serviceItem.getServiceItem(params);
	}

}

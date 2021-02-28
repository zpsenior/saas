package com.saas.goods.bo.staff;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.session.TenantStaffSession;
import com.saas.goods.dao.DAOService;
import com.saas.goods.dao.DAOServiceItem;
import com.saas.goods.request.QueryServiceParam;
import com.saas.goods.vo.Service;
import com.saas.goods.vo.ServiceItem;
import com.saas.pub.BOBase;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("Staff.QueryGoodsService")
public class QueryGoodsService extends BOBase {
	
	@Autowired
	private DAOService service;
	
	@Autowired
	private DAOServiceItem serviceItem;

	@Field("services")
	public List<Service> queryServiceList(@Var("params") QueryServiceParam params)throws Exception{
		TenantStaffSession session = getStaffSession();
		params.setTenantId(session.getTenantId());
		return service.queryServiceList(params);
	}

	@Field("service")
	public Service getService(@Var("serviceId") Long serviceId)throws Exception{
		Service params = new Service();
		TenantStaffSession session = getStaffSession();
		params.setTenantId(session.getTenantId());
		params.setServiceId(serviceId);
		return service.getService(params);
	}

	@Field("serviceItems")
	public List<ServiceItem> queryServiceItemList(@Var("params") QueryServiceParam params)throws Exception{
		TenantStaffSession session = getStaffSession();
		params.setTenantId(session.getTenantId());
		return serviceItem.queryServiceItemList(params);
	}
	
	public List<ServiceItem> queryServiceItemList(@Var("serviceId") long serviceId)throws Exception{
		QueryServiceParam params = new QueryServiceParam();
		TenantStaffSession session = getStaffSession();
		params.setTenantId(session.getTenantId());
		params.setServiceId(serviceId);
		return serviceItem.queryServiceItemList(params);
	}

	@Field("serviceItem")
	public ServiceItem getServiceItem(@Var("itemId") long itemId)throws Exception{
		ServiceItem params = new ServiceItem();
		TenantStaffSession session = getStaffSession();
		params.setTenantId(session.getTenantId());
		params.setServiceItemId(itemId);
		return serviceItem.getServiceItem(params);
	}
}

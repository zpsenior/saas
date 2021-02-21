package com.saas.goods.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.goods.dao.DAOService;
import com.saas.goods.dao.DAOServiceItem;
import com.saas.goods.request.QueryServiceParam;
import com.saas.goods.vo.Service;
import com.saas.goods.vo.ServiceItem;
import com.saas.goods.vo.ServiceSchedule;
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

	@Field("serviceSchedules")
	public List<ServiceSchedule> queryServiceScheduleList(@Var("tenantId") String tenantId, @Var("customerId") Long customerId)throws Exception{
		QueryServiceParam params = new QueryServiceParam();
		List<ServiceItem> items = serviceItem.queryServiceItemList(params);
		Map<Date, ServiceSchedule> maps = new TreeMap<>();
		for(ServiceItem item : items) {
			Date dt = item.getAppointDate();
			ServiceSchedule schedule = maps.get(dt);
			if(schedule == null) {
				schedule = new ServiceSchedule();
				schedule.setAppointDate(dt);
				maps.put(dt, schedule);
			}
			schedule.addItem(item);
		}
		return new ArrayList<ServiceSchedule>(maps.values());
	}
}

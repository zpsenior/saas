package com.saas.goods.bo.customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.session.CustomerSession;
import com.saas.goods.dao.DAOService;
import com.saas.goods.dao.DAOServiceItem;
import com.saas.goods.request.QueryServiceParam;
import com.saas.goods.vo.Service;
import com.saas.goods.vo.ServiceItem;
import com.saas.goods.vo.ServiceSchedule;
import com.saas.pub.BOBase;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("Customer.QueryGoodsService")
public class QueryGoodsService extends BOBase {
	
	@Autowired
	private DAOService service;
	
	@Autowired
	private DAOServiceItem serviceItem;

	@Field("services")
	public List<Service> queryServiceList(@Var("params") QueryServiceParam params)throws Exception{
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setCustomerId(session.getCustomerId());
		return service.queryServiceList(params);
	}

	@Field("service")
	public Service getService(@Var("serviceId") Long serviceId)throws Exception{
		Service params = new Service();
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setCustomerId(session.getCustomerId());
		params.setServiceId(serviceId);
		return service.getService(params);
	}
	
	public List<ServiceItem> queryServiceItemList(@Var("serviceId") long serviceId)throws Exception{
		QueryServiceParam params = new QueryServiceParam();
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setCustomerId(session.getCustomerId());
		params.setServiceId(serviceId);
		return serviceItem.queryServiceItemList(params);
	}

	@Field("serviceItem")
	public ServiceItem getServiceItem(@Var("itemId") long itemId)throws Exception{
		ServiceItem params = new ServiceItem();
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setCustomerId(session.getCustomerId());
		params.setServiceItemId(itemId);
		return serviceItem.getServiceItem(params);
	}

	@Field("serviceSchedules")
	public List<ServiceSchedule> queryServiceScheduleList()throws Exception{
		QueryServiceParam params = new QueryServiceParam();
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setCustomerId(session.getCustomerId());
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

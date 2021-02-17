package com.saas.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.saas.goods.request.QueryServiceParam;
import com.saas.goods.vo.Service;

public interface DAOService {

	@Select({"select * from goods_service where tenant_id=#{tenantId}"})
	public List<Service> queryServiceList(QueryServiceParam param)throws Exception;

	@Select({"select * from goods_service where tenant_id=#{tenantId} and service_id=#{serviceId}"})
	public Service getService(Service service)throws Exception;

	public void addService(Service service)throws Exception;
	
	
	public void updateService(Service service)throws Exception;

}

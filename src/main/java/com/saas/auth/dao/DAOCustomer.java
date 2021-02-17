package com.saas.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.saas.auth.request.QueryCustomerParam;
import com.saas.auth.vo.Customer;

public interface DAOCustomer {

	@Select({"select * from auth_customer where tenant_id=#{tenantId}"})
	public List<Customer> queryCustomerList(QueryCustomerParam param)throws Exception;

	@Select({"select * from auth_customer where tenant_id=#{tenantId} and customer_id=#{customerId}"})
	public Customer getCustomer(Customer customer)throws Exception;

	public void addCustomer(Customer customer)throws Exception;
	
	public void updateCustomer(Customer customer)throws Exception;

}

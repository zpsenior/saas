package com.saas.auth.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.dao.DAOCustomer;
import com.saas.auth.request.QueryCustomerParam;
import com.saas.auth.vo.Customer;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("QueryCustomer")
public class QueryCustomer {

	@Autowired
	private DAOCustomer customer;
	

	@Field("customers")
	public List<Customer> queryCustomerList(@Var("params") QueryCustomerParam params)throws Exception{
		return customer.queryCustomerList(params);
	}

	@Field("customer")
	public Customer getCustomer(@Var("tenantId") String tenantId, @Var("customerId") long customerId)throws Exception{
		Customer params = new Customer();
		params.setTenantId(tenantId);
		params.setCustomerId(customerId);
		return customer.getCustomer(params);
	}

}

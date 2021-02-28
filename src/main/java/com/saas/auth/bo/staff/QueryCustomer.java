package com.saas.auth.bo.staff;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.dao.DAOCustomer;
import com.saas.auth.request.QueryCustomerParam;
import com.saas.auth.session.TenantStaffSession;
import com.saas.auth.vo.Customer;
import com.saas.pub.BOBase;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("Staff.QueryCustomer")
public class QueryCustomer extends BOBase {

	@Autowired
	private DAOCustomer customer;
	

	@Field("customers")
	public List<Customer> queryCustomerList(@Var("params") QueryCustomerParam params)throws Exception{
		TenantStaffSession session = getStaffSession();
		params.setTenantId(session.getTenantId());
		return customer.queryCustomerList(params);
	}

	@Field("customer")
	public Customer getCustomer(@Var("customerId") long customerId)throws Exception{
		Customer params = new Customer();
		TenantStaffSession session = getStaffSession();
		params.setTenantId(session.getTenantId());
		params.setCustomerId(customerId);
		return customer.getCustomer(params);
	}

}

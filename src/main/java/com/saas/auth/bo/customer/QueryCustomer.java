package com.saas.auth.bo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.dao.DAOCustomer;
import com.saas.auth.session.CustomerSession;
import com.saas.auth.vo.Customer;
import com.saas.pub.BOBase;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

@Type
@Component("Customer.QueryCustomer")
public class QueryCustomer extends BOBase {

	@Autowired
	private DAOCustomer customer;

	@Field("customer")
	public Customer getCustomer()throws Exception{
		Customer params = new Customer();
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setCustomerId(session.getCustomerId());
		return customer.getCustomer(params);
	}

}

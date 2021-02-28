package com.saas.auth.bo.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.dao.DAOCustomer;
import com.saas.auth.session.TenantStaffSession;
import com.saas.auth.vo.Customer;
import com.saas.auth.vo.UserStatus;
import com.saas.pub.BOBase;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("Staff.MutationCustomer")
public class MutationCustomer extends BOBase {

	@Autowired
	private DAOCustomer customer;

	@Field("shieldAccount")
	public boolean shieldAccount(@Var("customerId") long customerId)throws Exception{
		TenantStaffSession session = new TenantStaffSession();
		Customer params = new Customer();
		params.setTenantId(session.getTenantId());
		params.setCustomerId(customerId);
		params.setStatus(UserStatus.OFFDUTY);
		customer.updateCustomer(params);
		return true;
	}

	@Field("unshieldAccount")
	public boolean unshieldAccount(@Var("customerId") long customerId)throws Exception{
		TenantStaffSession session = new TenantStaffSession();
		Customer params = new Customer();
		params.setTenantId(session.getTenantId());
		params.setCustomerId(customerId);
		params.setStatus(UserStatus.ONDUTY);
		customer.updateCustomer(params);
		return true;
	}
}

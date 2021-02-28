package com.saas.app.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.bo.admin.QueryAdmin;
import com.saas.auth.bo.admin.QueryPrivilege;
import com.saas.auth.bo.admin.QueryTenant;
import com.saas.payment.bo.QueryPayment;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;


@Component
@Type("Query")
public class AdminQuery {

	@Autowired
	private QueryAdmin admin;
	
	@Autowired
	private QueryTenant tenant;
	
	@Autowired
	private QueryPrivilege privilege;
	
	@Autowired
	private QueryPayment payment;
	
	@Field("admin")
	public QueryAdmin getAdmin() {
		return admin;
	}

	@Field("tenant")
	public QueryTenant getTenant() {
		return tenant;
	}
	
	@Field("privilege")
	public QueryPrivilege getPrivilege() {
		return privilege;
	}

	@Field("payment")
	public QueryPayment getPayment() {
		return payment;
	}
}

package com.saas.app.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.bo.admin.MutationAdmin;
import com.saas.auth.bo.admin.MutationPrivilege;
import com.saas.auth.bo.admin.MutationTenant;
import com.saas.payment.bo.MutationPayment;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;


@Component
@Type("Mutation")
public class AdminMutation {

	@Autowired
	private MutationAdmin admin;
	
	@Autowired
	private MutationTenant tenant;
	
	@Autowired
	private MutationPrivilege privilege;
	
	@Autowired
	private MutationPayment payment;

	@Field("admin")
	public MutationAdmin getAdmin() {
		return admin;
	}

	@Field("tenant")
	public MutationTenant getTenant() {
		return tenant;
	}
	
	@Field("privilege")
	public MutationPrivilege getPrivilege() {
		return privilege;
	}

	@Field("payment")
	public MutationPayment getPayment() {
		return payment;
	}

	
}

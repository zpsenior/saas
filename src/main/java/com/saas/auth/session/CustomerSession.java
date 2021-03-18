package com.saas.auth.session;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Type
public class CustomerSession extends Session {

	@Field
	private String tenantId;

	@Field
	private long customerId;

	@Field
	private long userId;
	
	public CustomerSession() {
		super("current.customer.thread.", "customer.session.");
	}

	@Field("sessionId")
	@Override
	public String getSessionId() {
		return getSessionKey();
	}

	public boolean isMale() {
		return false;
	}
}

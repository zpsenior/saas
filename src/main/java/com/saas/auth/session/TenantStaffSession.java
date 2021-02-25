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
public class TenantStaffSession extends Session{

	@Field
	private String tenantId;

	@Field
	private long staffId;

	@Field
	private long userId;
	
	public TenantStaffSession() {
		super("current.staff.thread.", "staff.session.");
	}

	@Field("sessionId")
	@Override
	public String getSessionId() {
		return getSessionKey();
	}
}

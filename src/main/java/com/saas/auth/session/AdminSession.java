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
public class AdminSession extends Session {

	@Field
	private long adminId;
	
	public AdminSession() {
		super("current.admin.thread.", "admin.session.");
	}

	@Field("sessionId")
	@Override
	public String getSessionId() {
		return getSessionKey();
	}
}

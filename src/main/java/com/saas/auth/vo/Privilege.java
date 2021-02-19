package com.saas.auth.vo;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="权限表", incr="privilegeId")
public class Privilege {

	@Field(isKey=true, desc="权限ID")
	private long privilegeId;

	@Field(isKey=true, desc="权限范围(0-admin,1-staff)")
	private byte scope;

	@Field(desc="权限描述", len=100)
	private String  descript;

	@Field(desc="权限对应路径", len=1000)
	private String[]  urls;
}

package com.saas.auth.vo;

import java.util.List;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Join;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;


@Data
@Type(desc="管理员角色表")
public class AdminRole {

	@Field(isKey=true, desc="角色ID")
	private long roleId;

	@Field(desc="角色描述")
	private String  descript;

	@Field(desc="权限id,逗号分隔")
	private String[]  privileges;
	
	@Join(bind = "queryAdminRolePrivilegeList", params = { "roleId" })
	private List<Privilege> privilegeList;

}

package com.saas.auth.vo;

import java.util.Date;
import java.util.List;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Join;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;


@Data
@Type(desc="租户人员权限表", incr="roleId")
public class TenantStaffRole {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="角色ID")
	private long roleId;

	@Field(desc="角色描述", len=100)
	private String  descript;

	@Field(desc="权限id,逗号分隔", len=1000)
	private String[]  privileges;
	
	@Field(desc="修改时间")
	private Date updateDate;
	
	@Field(desc="创建时间")
	private Date createDate;

	@Join(request = "QueryPrivilege.queryStaffRolePrivilegeList", params = { "privileges" })
	private List<Privilege> privilegeList;

}

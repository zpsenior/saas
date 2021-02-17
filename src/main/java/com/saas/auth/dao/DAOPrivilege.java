package com.saas.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.saas.auth.request.QueryPrivilegeParam;
import com.saas.auth.vo.Privilege;
import com.saas.auth.vo.UserInfo;

public interface DAOPrivilege {

	@Select({"select * from auth_privilege where tenant_id=#{tenantId} and privilege_id in",
		" (select privilege_id from auth_user_privilege where user_id=#{userId})"})
	public List<Privilege> queryUserPrivilegeList(UserInfo user)throws Exception;

	@Select({"select * from auth_privilege where 1=1"})
	public List<Privilege> queryPrivilegeList(QueryPrivilegeParam params)throws Exception;

	@Select({"select * from auth_privilege where privilege_id=#{privilegeId}"})
	public Privilege getPrivilege(long privilegeId)throws Exception;
	
	public void addPrivilege(Privilege params)throws Exception;
	
	public void updatePrivilege(Privilege params)throws Exception;

}

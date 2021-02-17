package com.saas.auth.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.dao.DAOPrivilege;
import com.saas.auth.request.QueryPrivilegeParam;
import com.saas.auth.vo.Privilege;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("QueryPrivilege")
public class QueryPrivilege {
	

	@Autowired
	private DAOPrivilege privilege;

	@Field("privileges")
	public List<Privilege> queryPrivilegeList(@Var("params") QueryPrivilegeParam params)throws Exception{
		return privilege.queryPrivilegeList(params);
	}

	@Field("privilege")
	public Privilege getPrivilege(@Var("privilegeId") long privilegeId)throws Exception{
		return privilege.getPrivilege(privilegeId);
	}

}

package com.saas.auth.bo.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.dao.DAOPrivilege;
import com.saas.auth.vo.Privilege;
import com.saas.pub.BOBase;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("Admin.MutationPrivilege")
public class MutationPrivilege extends BOBase {
	

	@Autowired
	private DAOPrivilege privilege;

	@Field("add")
	public boolean addPrivilege(@Var("params") Privilege params)throws Exception{
		privilege.addPrivilege(params);
		return true;
	}

	@Field("update")
	public boolean updatePrivilege(@Var("params") Privilege params)throws Exception{
		privilege.updatePrivilege(params);
		return true;
	}

}

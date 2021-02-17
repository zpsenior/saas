package com.saas.auth.bo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.dao.DAOPrivilege;
import com.saas.auth.vo.Privilege;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("MutationPrivilege")
public class MutationPrivilege {
	

	@Autowired
	private DAOPrivilege privilege;

	@Field("add")
	public void addPrivilege(@Var("params") Privilege params)throws Exception{
		privilege.addPrivilege(params);
	}

	@Field("update")
	public void updatePrivilege(@Var("params") Privilege params)throws Exception{
		privilege.updatePrivilege(params);
	}

}

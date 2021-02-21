package com.saas.goods.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.goods.dao.DAOService;
import com.saas.goods.request.ServiceParam;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("MutationGoodsService")
public class MutationGoodsService {
	
	@Autowired
	private DAOService service;

	@Field("create")
	public boolean createService(@Var("params") ServiceParam params)throws Exception{
		service.addService(params);
		return true;
	}
	
	@Field("appointment")
	public boolean appointment(@Var("params") ServiceParam params) throws Exception{
		service.updateService(params);
		return true;
	}

}

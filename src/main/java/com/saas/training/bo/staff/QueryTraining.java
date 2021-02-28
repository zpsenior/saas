package com.saas.training.bo.staff;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.pub.BOBase;
import com.saas.training.dao.DAOTraining;
import com.saas.training.vo.Training;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("Staff.QueryTraining")
public class QueryTraining extends BOBase {
	
	@Autowired
	private DAOTraining training;

	@Field("training")
	public Training getTraining(@Var("tenantId") String tenantId)throws Exception{
		Training params = new Training();
		params.setTenantId(tenantId);
		return training.getTraining(params);
	}
}

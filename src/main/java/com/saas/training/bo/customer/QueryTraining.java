package com.saas.training.bo.customer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.session.CustomerSession;
import com.saas.pub.BOBase;
import com.saas.training.dao.DAOTraining;
import com.saas.training.vo.Training;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

@Type
@Component("Customer.QueryTraining")
public class QueryTraining extends BOBase {
	
	@Autowired
	private DAOTraining training;

	@Field("training")
	public Training getTraining()throws Exception{
		CustomerSession session = getCustomerSession();
		Training params = new Training();
		params.setTenantId(session.getTenantId());
		return training.getTraining(params);
	}
	
}

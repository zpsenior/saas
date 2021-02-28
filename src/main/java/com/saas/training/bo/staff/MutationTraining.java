package com.saas.training.bo.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.pub.BOBase;
import com.saas.training.dao.DAOTraining;
import com.saas.training.vo.Training;
import com.zpsenior.graphql4j.annotation.Type;

@Type
@Component("Staff.MutationTraining")
public class MutationTraining extends BOBase {
	
	@Autowired
	private DAOTraining training;
	
	public boolean updateTraining(Training params) throws Exception{
		training.updateTraining(params);
		return true;
	}

}

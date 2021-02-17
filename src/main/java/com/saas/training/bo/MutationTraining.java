package com.saas.training.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.training.dao.DAOTeacher;
import com.saas.training.dao.DAOTraining;
import com.saas.training.vo.Teacher;
import com.saas.training.vo.Training;
import com.zpsenior.graphql4j.annotation.Type;

@Type
@Component("MutationTraining")
public class MutationTraining {
	
	@Autowired
	private DAOTeacher teacher;
	
	@Autowired
	private DAOTraining training;

	
	public void addTeacher(Teacher params) throws Exception{
		teacher.addTeacher(params);
	}

	
	public void updateTeacher(Teacher params) throws Exception{
		teacher.updateTeacher(params);
	}
	
	public void updateTraining(Training params) throws Exception{
		training.updateTraining(params);
	}

}

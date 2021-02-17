package com.saas.training.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.training.dao.DAOTeacher;
import com.saas.training.dao.DAOTraining;
import com.saas.training.request.QueryTeacherParam;
import com.saas.training.request.QueryTrainingParam;
import com.saas.training.vo.Teacher;
import com.saas.training.vo.Training;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("QueryTraining")
public class QueryTraining {
	
	@Autowired
	private DAOTeacher teacher;
	
	@Autowired
	private DAOTraining training;

	@Field("trainings")
	public List<Training> queryTrainingList(@Var("params") QueryTrainingParam params)throws Exception{
		return training.queryTrainingList(params);
	}

	@Field("training")
	public Training getTraining(@Var("tenantId") String tenantId)throws Exception{
		Training params = new Training();
		params.setTenantId(tenantId);
		return training.getTraining(params);
	}

	@Field("teachers")
	public List<Teacher> queryTeacherList(@Var("params") QueryTeacherParam params)throws Exception{
		return teacher.queryTeacherList(params);
	}

	@Field("teacher")
	public Teacher getTeacher(@Var("tenantId") String tenantId, @Var("staffId") long staffId)throws Exception{
		Teacher params = new Teacher();
		params.setTenantId(tenantId);
		params.setStaffId(staffId);
		return teacher.getTeacher(params);
	}
}

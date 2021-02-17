package com.saas.training.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.saas.training.request.QueryTrainingParam;
import com.saas.training.vo.Training;

public interface DAOTraining {
	
	@Select("select * from training_teacher where tenant_id=#{tenantId}")
	public List<Training> queryTrainingList(QueryTrainingParam params)throws Exception;

	@Select("select * from training_teacher where tenant_id=#{tenantId} and training_id=#{trainingId}")
	public Training getTraining(Training training)throws Exception;
	
	public void addTraining(Training training)throws Exception;
	
	public void updateTraining(Training training)throws Exception;

}

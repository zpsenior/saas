package com.saas.training.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.saas.training.request.QueryTrainingParam;
import com.saas.training.vo.Training;

public interface DAOTraining {
	
	@Select("select * from training_teacher where tenant_id=#{tenantId}")
	public List<Training> queryTrainingList(QueryTrainingParam params)throws Exception;

	@Select("select * from training_teacher where tenant_id=#{tenantId} and training_id=#{trainingId}")
	public Training getTraining(Training training)throws Exception;
	
	@Insert({
		"insert into training_training(",
		"     tenant_id,   descript,   address,  create_date",
		")values(",
		"   #{tenantId},#{descript},#{address},   now()   )"
		})
	public void addTraining(Training training)throws Exception;
	
	@Update({
		"update training_training",
		"<trim prefix='set' suffixOverrides=','>",
		"   <if test=' descript != null'>descript=#{descript}</if>",
		"   <if test=' address != null'>address=#{address}</if>",
		"   <if test='true'>update_date=now()</if>",
		"</trim>",
		"where tenant_id=#{tenantId}"
		})
	public void updateTraining(Training training)throws Exception;

}

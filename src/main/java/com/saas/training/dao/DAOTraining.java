package com.saas.training.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.saas.training.request.QueryTrainingParam;
import com.saas.training.vo.Training;

@Mapper
public interface DAOTraining {
	
	@Select("select * from training_teacher where tenant_id=#{tenantId}")
	public List<Training> queryTrainingList(QueryTrainingParam params)throws Exception;

	@Select("select * from training_teacher where tenant_id=#{tenantId} and training_id=#{trainingId}")
	public Training getTraining(Training training)throws Exception;
	
	@Insert({
		"insert into training_training(",
		"     tenant_id,   description,   address,  create_date",
		")values(",
		"   #{tenantId},#{description},#{address},   now()   )"
		})
	public void addTraining(Training training)throws Exception;
	
	@Update({
		"<script>",
		"update training_training",
		"<trim prefix='set' suffixOverrides=','>",
		"   <if test=' description != null'>description=#{description}</if>",
		"   <if test=' address != null'>address=#{address}</if>",
		"   <if test='true'>update_date=now()</if>",
		"</trim>",
		"where tenant_id=#{tenantId}",
		"</script>"
		})
	public void updateTraining(Training training)throws Exception;

}

package com.saas.training.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.saas.training.request.QueryTeacherCircleParam;
import com.saas.training.vo.TeacherCircle;

public interface DAOTeacherCircle {
	
	@Select("select * from training_teacher_circle where tenant_id=#{tenantId} and staff_id=#{staffId}")
	public List<TeacherCircle> queryTeacherCircleList(QueryTeacherCircleParam params)throws Exception;
	
	@Select("select * from training_teacher_circle where tenant_id=#{tenantId} and staff_id=#{staffId} and circle_id=#{circleId}")
	public void getTeacherCircle(TeacherCircle params)throws Exception;
	
	@Insert({
		"insert into training_teacher_circle(",
		"     tenant_id,  staff_id,   content,   imgs,   status,  create_date",
		")values(",
		"   #{tenantId},#{staffId},#{content},#{imgs},#{status},   now()   )"
		})
	public void addTeacherCircle(TeacherCircle params)throws Exception;
	
	@Update({
		"update training_teacher_circle",
		"<trim prefix='set' suffixOverrides=','>",
		"   <if test=' content != null'>content=#{content}</if>",
		"   <if test=' imgs != null'>imgs=#{imgs}</if>",
		"   <if test=' status != null'>status=#{status}</if>",
		"   <if test='true'>update_date=now()</if>",
		"</trim>",
		"where tenant_id=#{tenantId}",
		"  and staff_id=#{staffId}",
		"  and circle_id=#{circleId}"
		})
	public void updateTeacherCircle(TeacherCircle params)throws Exception;

}

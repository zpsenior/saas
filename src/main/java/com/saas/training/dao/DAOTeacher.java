package com.saas.training.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.saas.training.request.QueryTeacherParam;
import com.saas.training.vo.Teacher;


public interface DAOTeacher {
	
	@Select("select * from training_teacher where tenant_id=#{tenantId}")
	public List<Teacher> queryTeacherList(QueryTeacherParam params)throws Exception;

	@Select("select * from training_teacher where tenant_id=#{tenantId} and staff_id=#{staffId}")
	public Teacher getTeacher(Teacher teacher)throws Exception;
	
	public void addTeacher(Teacher teacher)throws Exception;
	
	public void updateTeacher(Teacher teacher)throws Exception;

}

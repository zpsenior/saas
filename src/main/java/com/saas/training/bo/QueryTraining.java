package com.saas.training.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.training.dao.DAOTeacher;
import com.saas.training.dao.DAOTeacherCircle;
import com.saas.training.dao.DAOTeacherReview;
import com.saas.training.dao.DAOTraining;
import com.saas.training.request.QueryTeacherCircleParam;
import com.saas.training.request.QueryTeacherParam;
import com.saas.training.request.QueryTeacherReviewParam;
import com.saas.training.request.QueryTrainingParam;
import com.saas.training.vo.Teacher;
import com.saas.training.vo.TeacherCircle;
import com.saas.training.vo.TeacherReview;
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
	private DAOTeacherReview teacherReview;
	
	@Autowired
	private DAOTeacherCircle teacherCircle;
	
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

	@Field("teacherReviews")
	public List<TeacherReview> queryTeacherReviewList(@Var("tenantId") String tenantId, @Var("staffId") long staffId)throws Exception{
		QueryTeacherReviewParam params = new QueryTeacherReviewParam();
		params.setTenantId(tenantId);
		params.setStaffId(staffId);
		return teacherReview.queryTeacherReviewList(params);
	}

	@Field("teacherCircles")
	public List<TeacherCircle> queryTeacherCircleList(@Var("tenantId") String tenantId, @Var("staffId") long staffId)throws Exception{
		QueryTeacherCircleParam params = new QueryTeacherCircleParam();
		params.setTenantId(tenantId);
		params.setStaffId(staffId);
		return teacherCircle.queryTeacherCircleList(params);
	}
}
